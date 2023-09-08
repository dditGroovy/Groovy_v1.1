package kr.co.groovy.sanction;

import kr.co.groovy.vo.SanctionFormatVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface SanctionMapper {

    SanctionFormatVO loadFormat (String format);

    String getSeq(String formatSanctnKnd);

    int getStatus(@Param("elctrnSanctnDrftEmplId") String elctrnSanctnDrftEmplId, @Param("commonCodeSanctProgrs") String commonCodeSanctProgrs);
}
