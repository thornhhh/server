package com.call110.common.security;

import java.util.Map;

import javax.annotation.PostConstruct;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.shiro.config.Ini;
import org.apache.shiro.config.Ini.Section;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.util.CollectionUtils;
import org.apache.shiro.web.config.IniFilterChainResolverFactory;
import org.apache.shiro.web.filter.mgt.DefaultFilterChainManager;
import org.apache.shiro.web.filter.mgt.PathMatchingFilterChainResolver;
import org.apache.shiro.web.servlet.AbstractShiroFilter;

public abstract class AbstractFilterChainDefinitionsService implements FilterChainDefinitionsService {
	private final static Log log = LogFactory.getLog(AbstractFilterChainDefinitionsService.class);  
    private String definitionFilePath = "";
//    @Autowired
    private ShiroFilterFactoryBean shiroFilter;
    @PostConstruct
    public void init() {
    	updatePermission();
        log.debug("initialize shiro permission success...");
    }  
    public AbstractFilterChainDefinitionsService(ShiroFilterFactoryBean shiroFilter){
    	this.shiroFilter = shiroFilter;
    }
    
    public void updatePermission() {
        synchronized (shiroFilter) {
            AbstractShiroFilter abstractShiroFilter = null;
            try {
            	abstractShiroFilter = (AbstractShiroFilter) shiroFilter.getObject();
            } catch (Exception e) {
                log.error(e.getMessage());
            }
            // 获取过滤管理器  
            PathMatchingFilterChainResolver filterChainResolver = (PathMatchingFilterChainResolver) abstractShiroFilter.getFilterChainResolver();
            DefaultFilterChainManager manager = (DefaultFilterChainManager) filterChainResolver.getFilterChainManager();
            // 清空初始权限配置
            manager.getFilterChains().clear();
            shiroFilter.getFilterChainDefinitionMap().clear();
            // 重新构建生成
            Map<String, String> chains = obtainPermission();
//            chains.entrySet().stream().forEach(e->manager.createChain(e.getKey(),e.getValue().trim().replace(" ", "")));
            for (Map.Entry<String, String> entry : chains.entrySet()) {
                String url = entry.getKey();
                String chainDefinition = entry.getValue().trim().replace(" ", "");
                manager.createChain(url, chainDefinition);
            }
            log.debug("update shiro permission success...");
        }
    }
  
    /** 读取配置资源 */  
    private Section obtainPermission() {
        Ini ini = new Ini();
        ini.loadFromPath(definitionFilePath);
//        ini.load(definitions); // 加载资源文件节点串
        Section section = ini.getSection(IniFilterChainResolverFactory.URLS); // 使用默认节点
        if (CollectionUtils.isEmpty(section)) {
            section = ini.getSection(Ini.DEFAULT_SECTION_NAME); // 如不存在默认节点切割,则使用空字符转换
        }
        Map<String, String> permissionMap = initOtherPermission();
        if (permissionMap != null && !permissionMap.isEmpty()) {
            section.putAll(permissionMap);
        }
        return section;
    }  
  
  
	public ShiroFilterFactoryBean getShiroFilter() {
		return shiroFilter;
	}

	public void setShiroFilter(ShiroFilterFactoryBean shiroFilter) {
		this.shiroFilter = shiroFilter;
	}
	public String getDefinitionFilePath() {
		return definitionFilePath;
	}
	public void setDefinitionFilePath(String definitionFilePath) {
		this.definitionFilePath = definitionFilePath;
	}
}
