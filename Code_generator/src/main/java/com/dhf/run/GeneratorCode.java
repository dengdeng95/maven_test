package com.dhf.run;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
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

import com.dhf.common.Column;
import com.dhf.common.Table;
import com.dhf.jdbc.DBUtils;

import freemarker.template.Configuration;
import freemarker.template.Template;

/**
 * GeneratorCode.java
 * @author denghf
 * 2017年7月31日 下午8:52:37
 */
@Mojo(name = "generator")
public class GeneratorCode extends AbstractMojo{
	
	public static Properties config;
	
	@Parameter(property = "project", required = true, readonly = true)
	private MavenProject project;
	
	/**
	 * Code_generator:generator -Dname=abc  name就是变量名,如果定义为str,就是-Dstr
	 */
	/*@Parameter(property = "name",defaultValue="main")
	private String name;*/
	
	public void execute() throws MojoExecutionException, MojoFailureException {
		getLog().info("插件自定义成功:"+project);
		
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
		//InputStream ins = GeneratorCode.class.getClassLoader().getResourceAsStream("generator.properties");
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
		
		Table tabel = DBUtils.getTabel(config.getProperty("table"));
		generator(tabel,javaPath);
	}
	
	/**
	 * 生成各种东西了
	 */
	public void generator(Table tabel,String javaPath){
		model(tabel,javaPath);
		modelExample(tabel,javaPath);
		mapper(tabel, javaPath);
		dao(tabel, javaPath);
		service(tabel, javaPath);
		serviceImpl(tabel, javaPath);
		controller(tabel, javaPath);
	}
	
	/**
	 * 生成model
	 * 2017年8月1日20:51:42
	 */
	public void model(Table tabel,String javaPath){
		getLog().info("---------------------------------Model生成中-------------------------------------------");
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Map map = new HashMap();
		
		map.put("author", config.getProperty("author"));
		map.put("date", sf.format(new Date()));
		map.put("model", config.getProperty("model"));
		map.put("columnsType", tabel.getColumnsType());//字段类型
		map.put("columns", tabel.getColumns());//字段
		map.put("package", config.getProperty("package"));
		map.put("table", config.getProperty("table"));
		String javapath = javaPath+"\\"+config.getProperty("package").replace(".", "\\")+"\\model\\"+config.getProperty("model")+".java";
		common(map, "model.ftl",javapath);
		getLog().info("---------------------------------Model生成完成-------------------------------------------");
	}
	
	/**
	 * 生成modelExample
	 * 2017年8月2日20:37:27
	 */
	public void modelExample(Table tabel,String javaPath){
		getLog().info("---------------------------------modelExample生成中-------------------------------------------");
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Map map = new HashMap();
		map.put("author", config.getProperty("author"));
		map.put("date", sf.format(new Date()));
		map.put("model", config.getProperty("model"));
		map.put("columnsType", tabel.getColumnsType());//字段类型
		map.put("columns", tabel.getColumns());//字段
		map.put("package", config.getProperty("package"));
		String javapath = javaPath+"\\"+config.getProperty("package").replace(".", "\\")+"\\model\\"+config.getProperty("model")+"Example"+".java";
		common(map, "modelExample.ftl",javapath);
		getLog().info("---------------------------------modelExample生成完成-------------------------------------------");
	}
	
	/**
	 * 生成mapper
	 * 2017年8月4日17:30:27
	 */
	public void mapper(Table tabel,String javaPath){
		getLog().info("---------------------------------mapper生成中-------------------------------------------");
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Map map = new HashMap();
		//准备字段
		String str = "";
		StringBuilder sb = new StringBuilder();
		for(int i=0;i<tabel.getColumns().size();i++){
			sb.append(tabel.getColumns().get(i).getColumn()+",");
		}
		str = sb.deleteCharAt(sb.length() - 1).toString();
		//主键ID
		String id = "";
		for(Column c : tabel.getColumns()){
			if("PRI".equals(c.getKey())){
				id = c.getColumn();
			}
		}
		//准备insert value值
		String insert_val = "";
		StringBuilder insert_val_sb = new StringBuilder();
		for(int i=0;i<tabel.getColumns().size();i++){
			//#{id,jdbcType=BIGINT}
			insert_val_sb.append("#{"+tabel.getColumns().get(i).getColumn()+",jdbcType="+tabel.getColumns().get(i).getMysqltype()+"},");
		}
		insert_val = insert_val_sb.deleteCharAt(insert_val_sb.length()-1).toString();
		
		//准备update value值
		String update_val = "";
		StringBuilder update_val_sb = new StringBuilder();
		for(int i=0;i<tabel.getColumns().size();i++){
			//id = #{record.id,jdbcType=BIGINT},
			update_val_sb.append(""+tabel.getColumns().get(i).getColumn()+" = #{record."+tabel.getColumns().get(i).getColumn()+",jdbcType="+tabel.getColumns().get(i).getMysqltype()+"},");
		}
		update_val = update_val_sb.deleteCharAt(update_val_sb.length()-1).toString();
		
		//准备update value值  但是是通过ID更改
		String update_val_id = "";
		StringBuilder update_val_sb_id = new StringBuilder();
		for(int i=0;i<tabel.getColumns().size();i++){
			if(!"PRI".equals(tabel.getColumns().get(i).getKey())){
				update_val_sb_id.append(""+tabel.getColumns().get(i).getColumn()+" = #{"+tabel.getColumns().get(i).getColumn()+",jdbcType="+tabel.getColumns().get(i).getMysqltype()+"},");
			}
		}
		update_val_id = update_val_sb_id.deleteCharAt(update_val_sb_id.length()-1).toString();
		
		map.put("base_Column_List", str);
		map.put("author", config.getProperty("author"));
		map.put("date", sf.format(new Date()));
		map.put("model", config.getProperty("model"));
		map.put("table", config.getProperty("table"));
		map.put("columns", tabel.getColumns());//字段
		map.put("id", id);//主键ID
		map.put("insert_val", insert_val);
		map.put("update_val", update_val);
		map.put("update_val_id", update_val_id);
		map.put("package", config.getProperty("package"));
		String javapath = javaPath+"\\"+config.getProperty("package").replace(".", "\\")+"\\mapper\\"+config.getProperty("model")+"Mapper"+".xml";
		common(map, "mapper.ftl",javapath);
		getLog().info("---------------------------------mapper生成完成-------------------------------------------");
	}
	
	/**
	 * 生成dao接口
	 * 2017年8月4日18:24:27
	 * @param tabel
	 * @param javaPath
	 */
	public void dao(Table tabel,String javaPath){
		getLog().info("---------------------------------dao生成中-------------------------------------------");
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Map map = new HashMap();
		map.put("author", config.getProperty("author"));
		map.put("date", sf.format(new Date()));
		map.put("model", config.getProperty("model"));
		map.put("package", config.getProperty("package"));
		map.put("baseDao", config.getProperty("baseDao"));
		String javapath = javaPath+"\\"+config.getProperty("package").replace(".", "\\")+"\\dao\\"+config.getProperty("model")+"Dao"+".java";
		common(map, "dao.ftl",javapath);
		getLog().info("---------------------------------dao生成完成-------------------------------------------");
	}
	
	/**
	 * 生成service接口
	 * 2017年8月4日18:28:16
	 * @param tabel
	 * @param javaPath
	 */
	public void service(Table tabel,String javaPath){
		getLog().info("---------------------------------service生成中-------------------------------------------");
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Map map = new HashMap();
		map.put("package", config.getProperty("package"));
		map.put("baseService", config.getProperty("baseService"));
		map.put("author", config.getProperty("author"));
		map.put("date", sf.format(new Date()));
		map.put("model", config.getProperty("model"));
		String javapath = javaPath+"\\"+config.getProperty("package").replace(".", "\\")+"\\service\\"+config.getProperty("model")+"Service"+".java";
		common(map, "service.ftl",javapath);
		getLog().info("---------------------------------service生成完成-------------------------------------------");
	}
	
	/**
	 * 生成serviceImpl
	 * 2017年8月4日18:29:51
	 * @param tabel
	 * @param javaPath
	 */
	public void serviceImpl(Table tabel,String javaPath){
		getLog().info("---------------------------------serviceImpl生成中-------------------------------------------");
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Map map = new HashMap();
		map.put("package", config.getProperty("package"));
		map.put("baseServiceImpl", config.getProperty("baseServiceImpl"));
		map.put("model", config.getProperty("model"));
		map.put("author", config.getProperty("author"));
		map.put("date", sf.format(new Date()));
		map.put("baseDao", config.getProperty("baseDao"));
		map.put("model_small", config.getProperty("model").substring(0,config.getProperty("model").length()-(config.getProperty("model").length()-1)).toLowerCase()+config.getProperty("model").substring(1));
		String javapath = javaPath+"\\"+config.getProperty("package").replace(".", "\\")+"\\service\\impl\\"+config.getProperty("model")+"ServiceImpl"+".java";
		common(map, "serviceImpl.ftl",javapath);
		getLog().info("---------------------------------serviceImpl生成完成-------------------------------------------");
	}
	
	/**
	 * 生成controller
	 * 2017年8月4日18:57:46
	 * @param tabel
	 * @param javaPath
	 */
	public void controller(Table tabel,String javaPath){
		getLog().info("---------------------------------controller生成中-------------------------------------------");
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Map map = new HashMap();
		map.put("author", config.getProperty("author"));
		map.put("date", sf.format(new Date()));
		map.put("package", config.getProperty("package"));
		map.put("model", config.getProperty("model"));
		String javapath = javaPath+"\\"+config.getProperty("package").replace(".", "\\")+"\\controller\\"+config.getProperty("model")+"Controller"+".java";
		common(map, "controller.ftl",javapath);
		getLog().info("---------------------------------controller生成完成-------------------------------------------");
	}
	
	/**
	 * 调用生成方法
	 * tabel 准备好的list,ftl 模板,ftlpath 路径
	 * @throws IOException 
	 */
	public void common(Map map,String ftl,String javapath){
		Writer writer = null;
		FileOutputStream fileOutputStream = null;
		OutputStreamWriter outputStreamWriter = null;
		try {
			Configuration c = new Configuration();
			//c.setDirectoryForTemplateLoading(new File("D:\\Users\\Ecliepse_dhf\\Code_generator\\src\\main\\java\\resources"));
			//读 设置为UTF-8
			c.setDefaultEncoding("UTF-8");
			c.setClassForTemplateLoading(this.getClass(), "/resources");
			File file = new File(javapath);
			if (!file.getParentFile().exists()){
				file.getParentFile().mkdirs();
	        }
			//加载目录下的模板
			Template t = c.getTemplate(ftl);
			
			//文件字节输出流
			fileOutputStream = new FileOutputStream(file);
			//写 设置为UTF-8  FileWrite无法设置  就要用输出流  FileOutputStream为OutputStream子类
			//把字节流转成字符流  不设置编码，解码过程将使用平台默认的字符编码   
			outputStreamWriter = new OutputStreamWriter(fileOutputStream,"UTF-8");
			//将文本写入字符输出流，缓冲各个字符，从而提供单个字符、数组和字符串的高效写入
			writer = new BufferedWriter(outputStreamWriter);
			
			t.process(map, writer);
			
			
			writer.close();
			outputStreamWriter.close();
			fileOutputStream.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			if(writer!=null){
				try {
					writer.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	
	public static void main(String[] args) {
		String[] str = new String[]{"a","b","c","d","e"};
		StringBuilder sb = new StringBuilder();
		for(int i=0;i<str.length;i++){
			sb.append(str[i]+",");
		}
		System.out.println(sb.toString());
		System.out.println(sb.deleteCharAt(sb.length()-1).toString());
		
		String s = "AbcSer";
		System.out.println(s.substring(0,s.length()-(s.length()-1)).toLowerCase()+s.substring(1));
	}
}
