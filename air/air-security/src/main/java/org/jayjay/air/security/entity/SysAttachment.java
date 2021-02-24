package org.jayjay.air.security.entity;

import java.time.LocalDateTime;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author JayJay
 * @since 2021-02-24
 */
@Data
  @EqualsAndHashCode(callSuper = false)
  @Accessors(chain = true)
public class SysAttachment implements Serializable {

    private static final long serialVersionUID=1L;

      /**
     * 主键
     */
        private String id;

      /**
     * 模块
     */
      private String moudle;

      /**
     * 业务id
     */
      private String businessId;

      /**
     * 文件类型
     */
      private String fileType;

      /**
     * 文件名称
     */
      private String fileName;

      /**
     * 文件大小
     */
      private Long fileSize;

      /**
     * 原来文件名称
     */
      private String originName;

      /**
     * 文件后缀
     */
      private String fileSuffix;

      /**
     * 文件路径
     */
      private String url;

      /**
     * 创建人
     */
      private String createBy;

      /**
     * 创建时间
     */
      private LocalDateTime createTime;

      /**
     * 修改人
     */
      private String updateBy;

      /**
     * 修改时间
     */
      private LocalDateTime updateTime;


}
