package com.gwf.family.business.bblog.dto;

import com.gwf.family.business.bcategory.entity.BCategory;
import com.gwf.family.business.bcomment.dto.CommentDTO;
import com.gwf.family.business.bcomment.entity.BComment;
import com.gwf.family.business.blabel.entity.BLabel;
import com.gwf.family.business.buserinfo.entity.BUserInfo;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Comparator;
import java.util.Date;
import java.util.List;

/**
 * Created by gaowenfeng on 2017/11/7.
 */
@Data
public class BlogResponseDTO implements Comparable<BlogResponseDTO>{
    @ApiModelProperty(value = "id")
    private Integer id;
    @ApiModelProperty(value = "作者")
    private BUserInfo author;
    /** 关键字 */
    @ApiModelProperty(value = "关键字，逗号隔开")
    private String keyword;
    /** 标题 */
    @ApiModelProperty(value = "标题")
    private String title;
    /** 内容 */
    @ApiModelProperty(value = "内容，查询详情时有值，查询列表时无值")
    private String content;
    /** 封面url */
    @ApiModelProperty(value = "封面url")
    private String coverUrl;
    @ApiModelProperty(value = "标签列表")
    private List<BLabel> labels;
    @ApiModelProperty(value = "分类")
    private BCategory category;
    @ApiModelProperty(value = "点赞数")
    private Integer favCount;
    @ApiModelProperty(value = "评论数")
    private Integer commentCount;
    @ApiModelProperty(value = "评论列表,查询详情时有值，查询列表无值")
    private List<CommentDTO> comments;
    @ApiModelProperty(value = "访问量")
    private Integer visitCount;
    @ApiModelProperty(value = "发布时间")
    private Date releaseTime;


    @Override
    public int compareTo(BlogResponseDTO o) {
        if(this.visitCount>o.visitCount){
            return 1;
        }else if(this.visitCount < o.visitCount){
            return -1;
        }else {
            return this.releaseTime.compareTo(o.getReleaseTime());
        }
    }
}
