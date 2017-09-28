package com.dhf.run;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.apache.maven.model.Resource;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;
import org.apache.maven.project.MavenProject;


/**
 * RemoveCode.java
 * @author denghf
 * 2017年8月4日 下午7:40:56
 */
@Mojo(name = "remove")
public class RemoveCode extends AbstractMojo{

	public static Properties config;
	@Parameter(property = "project", required = true, readonly = true)
	private MavenProject project;
	
	public void execute() throws MojoExecutionException, MojoFailureException {
		// TODO Auto-generated method stub
		List<Resource> resources = project.getResources();
		//配置文件路径
		String resourcePath = "";
		//java要路径
		String javaPath = "";
		//获得根路径
		for (Resource resource : resources) {
			if(resource.getDirectory().contains("resources")){
				resourcePath = resource.getDirectory();
			}
			if(resource.getDirectory().contains("java")){
				javaPath = resource.getDirectory();
			}
		}
		
		//获得配置文件
		config = new Properties();
		
		//获取当前项目下的generator.properties   最后的效果肯定是哪个项目依赖了这个插件  就取哪个项目的配置文件
		InputStream ins = GeneratorCode.class.getClassLoader().getResourceAsStream("generator.properties");
		try {
			//所以
			config.load(new InputStreamReader(new FileInputStream(new File(resourcePath+"\\generator.properties"))));
			//config.load(ins);
		} catch (Exception e) {
			e.printStackTrace();
		}
		getLog().info("----------------------------输出配置文件参数----------------------------------");
		Iterator it = config.entrySet().iterator();
		while(it.hasNext()){
			Map.Entry entry = (Map.Entry) it.next(); 
			getLog().info("-----"+entry.getKey()+":"+entry.getValue());
		}
		
		remove(javaPath);
	}
	
	/**
	 * 删除各种东西了
	 */
	public void remove(String javaPath){
		model(javaPath);
		modelExample(javaPath);
		dao(javaPath);
		mapper(javaPath);
		service(javaPath);
		serviceImpl(javaPath);
		controller(javaPath);
	}

	/**
	 * 删除model
	 * @param javaPath
	 */
	public void model(String javaPath){
		getLog().info("----------------------------删除model----------------------------------");
		String javaP = javaPath+"\\"+config.getProperty("package").replace(".", "\\")+"\\model\\"+config.getProperty("model")+".java";
		deletefile("model", javaP);
	}
	
	/**
	 * 删除modelExample
	 * @param javaPath
	 */
	public void modelExample(String javaPath){
		getLog().info("----------------------------删除modelExample----------------------------------");
		String javaP = javaPath+"\\"+config.getProperty("package").replace(".", "\\")+"\\model\\"+config.getProperty("model")+"Example"+".java";
		deletefile("modelExample", javaP);
	}
	
	/**
	 * 删除dao
	 * @param javaPath
	 */
	public void dao(String javaPath){
		getLog().info("----------------------------删除dao----------------------------------");
		String javaP = javaPath+"\\"+config.getProperty("package").replace(".", "\\")+"\\dao\\"+config.getProperty("model")+"Dao"+".java";
		deletefile("dao", javaP);
	}
	
	/**
	 * 删除mapper
	 * @param javaPath
	 */
	public void mapper(String javaPath){
		getLog().info("----------------------------删除mapper----------------------------------");
		String javaP = javaPath+"\\"+config.getProperty("package").replace(".", "\\")+"\\mapper\\"+config.getProperty("model")+"Mapper"+".xml";
		deletefile("mapper", javaP);
	}
	
	/**
	 * 删除service
	 * @param javaPath
	 */
	public void service(String javaPath){
		getLog().info("----------------------------删除service----------------------------------");
		String javaP = javaPath+"\\"+config.getProperty("package").replace(".", "\\")+"\\service\\"+config.getProperty("model")+"Service"+".java";
		deletefile("service", javaP);
	}
	
	/**
	 * 删除serviceImpl
	 * @param javaPath
	 */
	public void serviceImpl(String javaPath){
		getLog().info("----------------------------删除ServiceImpl----------------------------------");
		String javaP = javaPath+"\\"+config.getProperty("package").replace(".", "\\")+"\\service\\impl\\"+config.getProperty("model")+"ServiceImpl"+".java";
		deletefile("ServiceImpl", javaP);
	}
	
	/**
	 * 删除controller
	 * @param javaPath
	 */
	public void controller(String javaPath){
		getLog().info("----------------------------删除Controller----------------------------------");
		String javaP = javaPath+"\\"+config.getProperty("package").replace(".", "\\")+"\\controller\\"+config.getProperty("model")+"Controller"+".java";
		deletefile("Controller", javaP);
	}
	
	
	public void deletefile(String name,String javapath){
		File file = new File(javapath);
		if(file!=null){
			file.delete();
			getLog().info("----------------------------"+name+"删除成功！----------------------------------");
		}
	}
}
