package com.sprta.deliveryproject.service;

import com.sprta.deliveryproject.dto.ShopLikeResponseDto;
import com.sprta.deliveryproject.dto.ShopResponseDto;
import com.sprta.deliveryproject.entity.Member;
import com.sprta.deliveryproject.entity.Shop;
import com.sprta.deliveryproject.entity.ShopLike;
import com.sprta.deliveryproject.repository.MemberRepository;
import com.sprta.deliveryproject.repository.ShopRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ShopService {
    private final ShopRepository shopRepository;
    private final MemberRepository memberRepository;

    public List<ShopResponseDto> getMemberlikeshop(Long member_id, Member member) {
        Optional<Member> checkMember = memberRepository.findById(member_id);

        if(!checkMember.isPresent() || checkMember.get().getId() != member.getId()) {
            throw new IllegalArgumentException("해당 멤버가 존재하지 않거나, 현재 접속 멤버와 다릅니다.");
        }

        //member의 좋아요 리스트
        List<ShopLikeResponseDto> memberlikeshopList = checkMember.get().getShopLikeList().stream().map(ShopLikeResponseDto::new).toList();

        //좋아요한 shop 리스트
        List<ShopResponseDto> newShopList = new ArrayList<>();
        newShopList = memberlikeshopList.stream().map((shopLikeResponseDto -> {
            return new ShopResponseDto(shopLikeResponseDto.getShop());
        })).toList();

        return newShopList;
    }
}
