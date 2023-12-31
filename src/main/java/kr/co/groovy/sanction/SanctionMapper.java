package kr.co.groovy.sanction;

import kr.co.groovy.vo.ReferenceVO;
import kr.co.groovy.vo.SanctionFormatVO;
import kr.co.groovy.vo.SanctionLineVO;
import kr.co.groovy.vo.SanctionVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.security.core.parameters.P;

import java.util.List;
import java.util.Map;

@Mapper
public interface SanctionMapper {

    SanctionFormatVO loadFormat (String format);

    String getSeq(String formatSanctnKnd);

    int getStatus(@Param("elctrnSanctnDrftEmplId") String elctrnSanctnDrftEmplId, @Param("commonCodeSanctProgrs") String commonCodeSanctProgrs);

    List<SanctionVO> loadRequest(@Param("elctrnSanctnDrftEmplId")String emplId);

    void inputSanction(SanctionVO vo);
    void inputLine(SanctionLineVO vo);
    void inputRefrn(ReferenceVO vo);

    List<SanctionLineVO> loadAwaiting(@Param("progrsCode") String progrsCode, @Param("emplId") String emplId);

}
