package io.renren.modules.sys.entity;



import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class File implements Serializable {
 private static final long serialVersionUID = 1L;


        @TableId
        private Integer id;

       @ApiModelProperty(value = "资料分类")
       private String classify;

        @ApiModelProperty(value = "文件路径")
        private String path;

        @ApiModelProperty(value = "文件名称")
        private String fileName;

        @ApiModelProperty(value = "文件类型")
        private String fileType;

       private Date createTime;
       private String createBy;


}
