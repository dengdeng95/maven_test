package ${package!}.model;

import static javax.persistence.GenerationType.IDENTITY;
<#if columnsType??>
<#list columnsType as ct>
<#if ct=="BigDecimal" >
import java.math.BigDecimal;
<#elseif ct=='Date'>
import java.util.Date;
</#if>
</#list>
</#if>
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * @author:      ${author!}
 * @version:     V1.0 
 * @Date:        ${date!}
 */
@Table(name="${table!}")
public class ${model!} implements Serializable{
	
	<#if columns??>
	<#list columns as list>
	<#if list.key=='PRI'>
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "${(list.column)!}", unique = true, nullable = false)
	<#else>
	@Column(name = "${(list.column)!}")
	</#if>
	//${(list.comment)!}
	private ${(list.javatype)!} ${(list.column)!};
	</#list>
	</#if>
	
	<#if columns??>
	<#list columns as list>
	public ${(list.javatype)!} get${(list.getcolumn)!}(){
		return ${(list.column)!};
	}
	
	public void set${(list.getcolumn)!}(${(list.javatype)!} ${(list.column)!}){
		this.${(list.column)!}=${(list.column)!};
	}
	</#list>
	</#if>
}