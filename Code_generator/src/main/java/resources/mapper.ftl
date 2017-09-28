<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd" >
<mapper namespace="${package!}.dao.${model!}Dao">
  <resultMap id="BaseResultMap" type="${package!}.model.${model!}" >
  	<#if columns??>
	<#list columns as list>
	<#if list.key=='PRI'>
	<id column="${(list.column)!}" property="${(list.column)!}" jdbcType="${(list.mysqltype)!}" />
	<#else>
	<result column="${(list.column)!}" property="${(list.column)!}" jdbcType="${(list.mysqltype)!}" />
	</#if>
	</#list>
	</#if>
  </resultMap>
  <sql id="Example_Where_Clause" >
    <where >
      <foreach collection="oredCriteria" item="criteria" separator="or" >
        <if test="criteria.valid" >
          <trim prefix="(" suffix=")" prefixOverrides="and" >
            <foreach collection="criteria.criteria" item="criterion" >
              <choose >
                <when test="criterion.noValue" >
                  and ${r"${criterion.condition}"}
                </when>
                <when test="criterion.singleValue" >
                  and ${r"${criterion.condition} #{criterion.value}"}
                </when>
                <when test="criterion.betweenValue" >
                  and ${r"${criterion.condition} #{criterion.value} and #{criterion.secondValue}"}
                </when>
                <when test="criterion.listValue" >
                  and ${r"${criterion.condition}"}
                  <foreach collection="criterion.value" item="listItem" open="(" close=")" separator="," >
                    ${r"#{listItem}"}
                  </foreach>
                </when>
              </choose>
            </foreach>
          </trim>
        </if>
      </foreach>
    </where>
  </sql>
  <sql id="Update_By_Example_Where_Clause" >
    <where >
      <foreach collection="example.oredCriteria" item="criteria" separator="or" >
        <if test="criteria.valid" >
          <trim prefix="(" suffix=")" prefixOverrides="and" >
            <foreach collection="criteria.criteria" item="criterion" >
              <choose >
                <when test="criterion.noValue" >
                  and ${r"${criterion.condition}"}
                </when>
                <when test="criterion.singleValue" >
                  and ${r"${criterion.condition} #{criterion.value}"}
                </when>
                <when test="criterion.betweenValue" >
                  and ${r"${criterion.condition} #{criterion.value} and #{criterion.secondValue}"}
                </when>
                <when test="criterion.listValue" >
                  and ${r"${criterion.condition}"}
                  <foreach collection="criterion.value" item="listItem" open="(" close=")" separator="," >
                    ${r"#{listItem}"}
                  </foreach>
                </when>
              </choose>
            </foreach>
          </trim>
        </if>
      </foreach>
    </where>
  </sql>
  <sql id="Base_Column_List" >
  	${base_Column_List!}
  </sql>
  <select id="selectByExample" resultMap="BaseResultMap" parameterType="${package!}.model.${model!}Example" >
    select
    <if test="distinct" >
      distinct
    </if>
    <include refid="Base_Column_List" />
    from ${table!}
    <if test="_parameter != null" >
      <include refid="Example_Where_Clause" />
    </if>
    <if test="orderByClause != null" >
      order by ${r"${orderByClause}"}
    </if>
  </select>
  <select id="selectByPrimaryKey" resultMap="BaseResultMap" parameterType="java.lang.Long" >
    select 
    <include refid="Base_Column_List" />
    from ${table!}
    where ${id!} = ${r"#{"}${id!},jdbcType=BIGINT${r"}"}
  </select>
  <delete id="deleteByPrimaryKey" parameterType="java.lang.Long" >
    delete from ${table!}
    where ${id!} = ${r"#{"}${id!},jdbcType=BIGINT${r"}"}
  </delete>
  <delete id="deleteByExample" parameterType="${package!}.model.${model!}Example" >
    delete from ${table!}
    <if test="_parameter != null" >
      <include refid="Example_Where_Clause" />
    </if>
  </delete>
  <insert id="insert" parameterType="${package!}.model.${model!}" >
    insert into ${table!} (${base_Column_List!}
      )
    values (${insert_val!}
      )
  </insert>
  <insert id="insertSelective" parameterType="${package!}.model.${model!}" >
    insert into ${table!}
    <trim prefix="(" suffix=")" suffixOverrides="," >
      <#if columns??>
	  <#list columns as list>
	  <if test="${(list.column)!} != null" >
        ${(list.column)!},
      </if>
      </#list>
	  </#if>
    </trim>
    <trim prefix="values (" suffix=")" suffixOverrides="," >
      <#if columns??>
	  <#list columns as list>
	  <if test="${(list.column)!} != null" >
        ${r"#{"}${(list.column)!},jdbcType=${(list.mysqltype)!}${r"}"},
      </if>
      </#list>
	  </#if>
    </trim>
  </insert>
  <select id="countByExample" parameterType="${package!}.model.${model!}Example" resultType="java.lang.Integer" >
    select count(*) from ${table!}
    <if test="_parameter != null" >
      <include refid="Example_Where_Clause" />
    </if>
  </select>
  <select id="selectAll" resultMap="BaseResultMap">
    select * from ${table!}
  </select>
  <update id="updateByExampleSelective" parameterType="map" >
    update ${table!}
    <set>
      <#if columns??>
	  <#list columns as list>
	  <if test="record.${(list.column)!} != null" >
        ${(list.column)!} = ${r"#{"}record.${(list.column)!},jdbcType=${(list.mysqltype)!}${r"}"},
      </if>
      </#list>
	  </#if>
    </set>
    <if test="_parameter != null" >
      <include refid="Update_By_Example_Where_Clause" />
    </if>
  </update>
  <update id="updateByExample" parameterType="map" >
    update ${table!}
    set ${update_val!}
    <if test="_parameter != null" >
      <include refid="Update_By_Example_Where_Clause" />
    </if>
  </update>
  <update id="updateByPrimaryKeySelective" parameterType="${package!}.model.${model!}" >
    update ${table!}
    <set>
      <#if columns??>
	  <#list columns as list>
	  <if test="${(list.column)!} != null" >
        ${(list.column)!} = ${r"#{"}${(list.column)!},jdbcType=${(list.mysqltype)!}${r"}"},
      </if>
      </#list>
	  </#if>
    </set>
    where ${id!} = ${r"#{"}${id!},jdbcType=BIGINT${r"}"}
  </update>
  <update id="updateByPrimaryKey" parameterType="${package!}.model.${model!}" >
    update ${table!}
    set ${update_val_id!}
    where ${id!} = ${r"#{"}${id!},jdbcType=BIGINT${r"}"}
  </update>
</mapper>