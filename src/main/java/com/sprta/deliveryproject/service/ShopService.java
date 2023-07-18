package com.sprta.deliveryproject.service;

import com.sprta.deliveryproject.dto.ApiResponseDto;
import com.sprta.deliveryproject.dto.ShopResponseDto;
import com.sprta.deliveryproject.entity.Shop;
import com.sprta.deliveryproject.repository.ShopRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ShopService {
    private ShopRepository shopRepository;
    private final MemberRepository memberRepository;

    public ResponseEntity<ApiResponseDto> getCategoryShops(Long categoryId) {
        List<Shop> shopList = shopRepository.findAllByCategory_IdOrderByModifiedAtDesc(categoryId);

        List<ShopResponseDto> newShopList = shopList.stream().map(ShopResponseDto::new).toList();

        return ResponseEntity.status(200).body(new ApiResponseDto(HttpStatus.OK.value(),"카테고리 조회",newShopList));
    }

    public ResponseEntity<ApiResponseDto> getDetailShops(Long shopId) {
        Shop shop = FindShop(shopId);

        return ResponseEntity.status(200).body(new ApiResponseDto(HttpStatus.OK.value(), "특정 가게 조회", shop));
    }

    public Shop FindShop(Long id){
        return shopRepository.findById(id).orElseThrow(() ->
                        new IllegalArgumentException("해당 가게는 존재하지 않습니다. 다시 시도해주세요.")
                );
    }

    public List<ShopResponseDto> getMemberlikeshop(Long member_id, Member member) {
        Optional<Member> checkMember = memberRepository.findById(member_id);

        if (!checkMember.isPresent() || checkMember.get().getId() != member.getId()) {
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
