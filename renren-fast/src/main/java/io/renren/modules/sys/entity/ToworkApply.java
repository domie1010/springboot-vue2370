/**
 * .
 *
 * Thm
 *
 * ！
 */

package io.renren.modules.sys.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
@TableName("towork_apply")
public class ToworkApply implements Serializable {
	private static final long serialVersionUID = 1L;
	@TableId
	private Long id;
	private String title;
	private String content;
	private String  applyUser;
	@JsonFormat(pattern="yyyy-MM-dd")
	private Date  applyTime;
	private String bz;
	private String stu;
}
