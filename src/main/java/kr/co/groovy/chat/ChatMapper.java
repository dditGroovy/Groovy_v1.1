package kr.co.groovy.chat;

import kr.co.groovy.vo.ChatRoomVO;
import kr.co.groovy.vo.EmployeeVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface ChatMapper {

    List<EmployeeVO> loadEmpListForChat(String emplId);

    int insertChatRoom(Map<String, Object> data);

    int insertChatMember(String emplId);

    List<ChatRoomVO> loadChatRooms(String emplId);

}