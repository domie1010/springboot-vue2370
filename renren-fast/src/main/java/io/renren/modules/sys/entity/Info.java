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
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
@TableName("info")
public class Info implements Serializable {
	private static final long serialVersionUID = 1L;
	@TableId
	private Long id;
	private String title;
	private String content;
	private String  createBy;
	private Date  createTime;
	private String bz;
}
