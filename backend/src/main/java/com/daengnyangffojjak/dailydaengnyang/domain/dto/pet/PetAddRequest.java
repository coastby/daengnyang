package com.daengnyangffojjak.dailydaengnyang.domain.dto.pet;

import com.daengnyangffojjak.dailydaengnyang.domain.entity.Group;
import com.daengnyangffojjak.dailydaengnyang.domain.entity.Pet;
import com.daengnyangffojjak.dailydaengnyang.domain.entity.User;
import com.daengnyangffojjak.dailydaengnyang.domain.entity.enums.Sex;
import com.daengnyangffojjak.dailydaengnyang.domain.entity.enums.Species;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class PetAddRequest {

	private String name;
	private Species species;
	private String breed;
	private Sex sex;
	private LocalDate birthday;
	private double weight;
	private LocalDateTime createdAt;

	public Pet toEntity(Group group, User user) {
		return Pet.builder()
				.name(this.name)
				.species(this.species)
				.breed(this.breed)
				.sex(this.sex)
				.birthday(this.birthday)
				.weight(this.weight)
				.group(group)
				.user(user)
				.build();
	}
}
