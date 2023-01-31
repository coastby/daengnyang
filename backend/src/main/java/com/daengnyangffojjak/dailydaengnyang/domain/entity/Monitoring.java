package com.daengnyangffojjak.dailydaengnyang.domain.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Monitoring extends BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "pet_id")
	private Pet pet;
	@Column(nullable = false)
	private LocalDate date;          //오늘 이전 날짜

	private double weight;        //몸무게

	private boolean vomit;        //구토
	private boolean amPill;       //오전 투약
	private boolean pmPill;       //오후 투약
	private boolean customSymptom;           //custom symptom
	private String customSymptomName;        //custom symptom 이름

	private int feedToGram;        //식이량 (g)
	private int walkCnt;         //산책횟수 - 강아지
	private int playCnt;         //놀이횟수 - 고양이
	private int urination;        //배뇨 횟수
	private int defecation;       //배변 횟수
	private int respiratoryRate;  //호흡수
	private int customInt;        //custom 모니터링
	private String customIntName;

	private String notes;    //기타 특이사항

}