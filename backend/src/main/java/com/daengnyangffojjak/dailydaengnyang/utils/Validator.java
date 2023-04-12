package com.daengnyangffojjak.dailydaengnyang.utils;

import com.daengnyangffojjak.dailydaengnyang.domain.entity.Comment;
import com.daengnyangffojjak.dailydaengnyang.domain.entity.Disease;
import com.daengnyangffojjak.dailydaengnyang.domain.entity.Group;
import com.daengnyangffojjak.dailydaengnyang.domain.entity.Monitoring;
import com.daengnyangffojjak.dailydaengnyang.domain.entity.Notification;
import com.daengnyangffojjak.dailydaengnyang.domain.entity.NotificationUser;
import com.daengnyangffojjak.dailydaengnyang.domain.entity.Pet;
import com.daengnyangffojjak.dailydaengnyang.domain.entity.Record;
import com.daengnyangffojjak.dailydaengnyang.domain.entity.RecordFile;
import com.daengnyangffojjak.dailydaengnyang.domain.entity.Tag;
import com.daengnyangffojjak.dailydaengnyang.domain.entity.User;
import com.daengnyangffojjak.dailydaengnyang.domain.entity.UserGroup;
import com.daengnyangffojjak.dailydaengnyang.exception.CommentException;
import com.daengnyangffojjak.dailydaengnyang.exception.DiseaseException;
import com.daengnyangffojjak.dailydaengnyang.exception.ErrorCode;
import com.daengnyangffojjak.dailydaengnyang.exception.FileException;
import com.daengnyangffojjak.dailydaengnyang.exception.GroupException;
import com.daengnyangffojjak.dailydaengnyang.exception.MonitoringException;
import com.daengnyangffojjak.dailydaengnyang.exception.NotificationException;
import com.daengnyangffojjak.dailydaengnyang.exception.PetException;
import com.daengnyangffojjak.dailydaengnyang.exception.RecordException;
import com.daengnyangffojjak.dailydaengnyang.exception.RecordFileException;
import com.daengnyangffojjak.dailydaengnyang.exception.TagException;
import com.daengnyangffojjak.dailydaengnyang.exception.UserException;
import com.daengnyangffojjak.dailydaengnyang.repository.CommentRepository;
import com.daengnyangffojjak.dailydaengnyang.repository.DiseaseRepository;
import com.daengnyangffojjak.dailydaengnyang.repository.GroupRepository;
import com.daengnyangffojjak.dailydaengnyang.repository.MonitoringRepository;
import com.daengnyangffojjak.dailydaengnyang.repository.NotificationRepository;
import com.daengnyangffojjak.dailydaengnyang.repository.NotificationUserRepository;
import com.daengnyangffojjak.dailydaengnyang.repository.PetRepository;
import com.daengnyangffojjak.dailydaengnyang.repository.RecordFileRepository;
import com.daengnyangffojjak.dailydaengnyang.repository.RecordRepository;
import com.daengnyangffojjak.dailydaengnyang.repository.TagRepository;
import com.daengnyangffojjak.dailydaengnyang.repository.UserGroupRepository;
import com.daengnyangffojjak.dailydaengnyang.repository.UserRepository;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
@RequiredArgsConstructor
@Slf4j
public class Validator {

	private final UserRepository userRepository;
	private final UserGroupRepository userGroupRepository;
	private final GroupRepository groupRepository;
	private final PetRepository petRepository;
	private final MonitoringRepository monitoringRepository;
	private final RecordRepository recordRepository;
	private final TagRepository tagRepository;
	private final DiseaseRepository diseaseRepository;
	private final RecordFileRepository recordFileRepository;

	private final CommentRepository commentRepository;
	private final NotificationRepository notificationRepository;
	private final NotificationUserRepository notificationUserRepository;

	public User getUserById(Long userId) {
		return userRepository.findById(userId)
				.orElseThrow(() -> new UserException(ErrorCode.INVALID_REQUEST));
	}

	public User getUserByUserName(String userName) {
		log.info("getUserByUserName");
		return userRepository.findByUserName(userName)
				.orElseThrow(() -> new UserException(ErrorCode.USERNAME_NOT_FOUND));
	}

	public Group getGroupById(Long groupId) {    //그룹 아이디로 그룹 조회, 없으면 예외 발생
		return groupRepository.findById(groupId)
				.orElseThrow(() -> new GroupException(ErrorCode.GROUP_NOT_FOUND));
	}

	public Pet getPetById(Long petId) {
		return petRepository.findById(petId)
				.orElseThrow(() -> new PetException(ErrorCode.PET_NOT_FOUND));
	}

	public Monitoring getMonitoringById(Long monitoringId) {
		return monitoringRepository.findById(monitoringId)
				.orElseThrow(() -> new MonitoringException(ErrorCode.MONITORING_NOT_FOUND));
	}

	public Tag getTagById(Long tagId) {
		return tagRepository.findById(tagId)
				.orElseThrow(() -> new TagException(ErrorCode.TAG_NOT_FOUND));
	}

	public Disease getDiseaseById(Long diseaseId) {
		return diseaseRepository.findById(diseaseId)
				.orElseThrow(() -> new DiseaseException(ErrorCode.DISEASE_NOT_FOUND));
	}

	public RecordFile getRecordFileById(Long recordFileId) {
		return recordFileRepository.findById(recordFileId)
				.orElseThrow(() -> new RecordFileException(ErrorCode.RECORDFILE_NOT_FOUND));
	}

	//Pet과 username인 User가 같은 그룹이면 Pet을 반환
	public Pet getPetWithUsername(Long petId, String username) {
		Pet pet = getPetById(petId);
		List<UserGroup> userGroupList = getUserGroupListByUsername(pet.getGroup(),
				username);
		return pet;
	}

	public Record getRecordById(Long recordId) {
		return recordRepository.findById(recordId)
				.orElseThrow(() -> new RecordException(ErrorCode.RECORD_NOT_FOUND));
	}

	/**
	 * User가 Group에 속해 있으면 UserGroupList 반환 추후 ADMMIN도 가능하게 수정 예정
	 **/
	public List<UserGroup> getUserGroupListByUsername(Group group, String username) {
		User user = getUserByUserName(username);     //유저 확인
		//그룹으로 그룹 내 멤버 불러오기
		List<UserGroup> userGroupList = userGroupRepository.findAllByGroup(group);
		//로그인한 유저가 그룹 내 유저인지 확인 -> 그룹 내 유저가 아니면 예외 발생
		if (userGroupList.stream()
				.noneMatch(userGroup -> username.equals(userGroup.getUser().getUsername()))) {
			throw new GroupException(ErrorCode.INVALID_PERMISSION);
		}
		return userGroupList;
	}

	public Comment getCommentById(Long commentId) {
		return  commentRepository.findById(commentId)
				.orElseThrow(() -> new CommentException(ErrorCode.COMMENT_NOT_FOUND));
	}

	// 빈 파일을 업로드 하거나 파일을 업로드 안했을 때
	public void validateFile(List<MultipartFile> multipartFiles) {
		for (MultipartFile multipartFile : multipartFiles) {
			if (multipartFile.isEmpty()) {
				throw new FileException(ErrorCode.FILE_NOT_FOUND);
			}
		}
	}

	//유저아이디 : 그룹 내 역할 -> 맵 반환
	public Map<Long, String> makeMapWithRoleAndId(List<UserGroup> userGroupList) {
		Map<Long, String> roleIdMap = new HashMap<>();
		for (UserGroup userGroup : userGroupList) {
			Long userId = userGroup.getUser().getId();
			String role = userGroup.getRoleInGroup();

			roleIdMap.put(userId, role);
		}
		return roleIdMap;
	}

	public Notification getNotificationById(Long notificationId) {
		return notificationRepository.findById(notificationId)
				.orElseThrow(() -> new NotificationException(ErrorCode.NOTIFICATION_NOT_FOUND));
	}

	public NotificationUser validateNotificationUser(Long notificationId, Long loginUserId){
		return notificationUserRepository.findByNotificationIdAndUserId(
						notificationId, loginUserId)
				.orElseThrow(() -> new NotificationException(ErrorCode.INVALID_PERMISSION));
	}


}
