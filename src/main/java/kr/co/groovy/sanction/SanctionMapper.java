package kr.co.groovy.sanction;

import kr.co.groovy.vo.SanctionFormatVO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SanctionMapper {

    SanctionFormatVO loadFormat (String format);
}
