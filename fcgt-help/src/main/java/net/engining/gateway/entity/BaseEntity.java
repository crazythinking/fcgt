package net.engining.gateway.entity;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import net.engining.gateway.constants.DelFlagDef;

public class BaseEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4805900011042756684L;

	/**
	 * <p>
	 * 自增序列
	 * </p>
	 */
	protected String id;
	/**
	 * <p>
	 * 创建人
	 * </p>
	 */
	protected String createBy;
	/**
	 * <p>
	 * 创建日期
	 * </p>
	 */
	protected Date createDate;
	/**
	 * <p>
	 * 更新人
	 * </p>
	 */
	protected String updateBy;
	/**
	 * <p>
	 * 更新日期
	 * </p>
	 */
	protected Date updateDate;
	/**
	 * <p>
	 * 删除标志
	 * </p>
	 */
	protected DelFlagDef delFlag;
	/**
	 * <p>
	 * 乐观锁
	 * </p>
	 */
	protected Integer olVersion;
	/**
	 * <p>
	 * 当前页数
	 * </p>
	 */
	protected Integer pageNo;
	/**
	 * <p>
	 * 每页显示条数
	 * </p>
	 */
	protected Integer pageSize;

	public BaseEntity init(BaseEntity baseEntity) {
		baseEntity.setCreateBy("system");
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			baseEntity.setCreateDate(sdf.parse(sdf.format(new Date())));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		baseEntity.setDelFlag(DelFlagDef.N);
		baseEntity.setOlVersion(0);
		return baseEntity;
	}

	/**
	 * <p>
	 * 自增序列
	 * </p>
	 */
	public String getId() {
		return id;
	}

	/**
	 * <p>
	 * 自增序列
	 * </p>
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * <p>
	 * 创建人
	 * </p>
	 */
	public String getCreateBy() {
		return createBy;
	}

	/**
	 * <p>
	 * 创建人
	 * </p>
	 */
	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}

	/**
	 * <p>
	 * 创建日期
	 * </p>
	 */
	public Date getCreateDate() {
		return createDate;
	}

	/**
	 * <p>
	 * 创建日期
	 * </p>
	 */
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	/**
	 * <p>
	 * 更新人
	 * </p>
	 */
	public String getUpdateBy() {
		return updateBy;
	}

	/**
	 * <p>
	 * 更新人
	 * </p>
	 */
	public void setUpdateBy(String updateBy) {
		this.updateBy = updateBy;
	}

	/**
	 * <p>
	 * 更新日期
	 * </p>
	 */
	public Date getUpdateDate() {
		return updateDate;
	}

	/**
	 * <p>
	 * 更新日期
	 * </p>
	 */
	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	/**
	 * <p>
	 * 删除标志
	 * </p>
	 */
	public DelFlagDef getDelFlag() {
		return delFlag;
	}

	/**
	 * <p>
	 * 删除标志
	 * </p>
	 */
	public void setDelFlag(DelFlagDef delFlag) {
		this.delFlag = delFlag;
	}

	/**
	 * <p>
	 * 乐观锁
	 * </p>
	 */
	public Integer getOlVersion() {
		return olVersion;
	}

	/**
	 * <p>
	 * 乐观锁
	 * </p>
	 */
	public void setOlVersion(Integer olVersion) {
		this.olVersion = olVersion;
	}

	public Integer getPageNo() {
		return pageNo;
	}

	public void setPageNo(Integer pageNo) {
		this.pageNo = pageNo;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

}
