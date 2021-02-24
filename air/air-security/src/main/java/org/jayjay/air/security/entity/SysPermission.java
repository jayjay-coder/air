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
public class SysPermission implements Serializable {

    private static final long serialVersionUID=1L;

      /**
     * 主键
     */
        private String id;

      /**
     * 权限名称
     */
      private String permissionName;

      /**
     * 描述
     */
      private String descripe;

      /**
     * 权限标识
     */
      private String permission;

      /**
     * 菜单id
     */
      private String menuId;

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
