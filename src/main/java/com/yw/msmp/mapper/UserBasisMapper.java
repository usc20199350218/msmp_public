package com.yw.msmp.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yw.msmp.dto.SearchUserDTO;
import com.yw.msmp.dto.UserDTO;
import com.yw.msmp.entity.UserBasisEntity;
import com.yw.msmp.vo.SelectVO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserBasisMapper extends BaseMapper< UserBasisEntity > {

    IPage< UserDTO > selectMyPage( Page< UserBasisEntity > tbUserPage );

    IPage< SelectVO > selectVOPage( String searchType, String searchText, Page< SelectVO > selectVOPage );


    IPage< UserDTO > searchPage( @Param( "searchUserDTO" ) SearchUserDTO searchUserDTO,
                                 Page< UserBasisEntity > userPage );

}
