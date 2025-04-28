package com.pizzamukza.pizzahut.domain.admin.pizzamenu.dto;

public class PizzaDTO {

    private PizzaMenuDTO pizzaMenuDTO;
    private SizeDTO sizeDTO;

    public PizzaDTO() {
        this.pizzaMenuDTO = new PizzaMenuDTO();
        this.sizeDTO = new SizeDTO();
    }

    public PizzaDTO(PizzaMenuDTO pizzaMenuDTO, SizeDTO sizeDTO) {
        this.pizzaMenuDTO = pizzaMenuDTO;
        this.sizeDTO = sizeDTO;
    }

    public PizzaMenuDTO getPizzaMenuDTO() {
        return pizzaMenuDTO;
    }

    public void setPizzaMenuDTO(PizzaMenuDTO pizzaMenuDTO) {
        this.pizzaMenuDTO = pizzaMenuDTO;
    }

    public SizeDTO getSizeDTO() {
        return sizeDTO;
    }

    public void setSizeDTO(SizeDTO sizeDTO) {
        this.sizeDTO = sizeDTO;
    }

    // 편의 메서드 추가 (to access directly from PizzaDTO)
    public String getPizzaName() {
        return pizzaMenuDTO.getPizzaName();
    }

    public int getPizzaId() {
        return pizzaMenuDTO.getPizzaId();
    }

    public String getSizeName() {
        return sizeDTO.getSizeName();
    }

    public int getPrice() {
        return sizeDTO.getPrice();
    }

    public int getQuantity() {
        return sizeDTO.getQuantity();
    }

    public PizzaDTO(SizeDTO sizeDTO) {
        this.sizeDTO = sizeDTO;
    }

    public SizeDTO getSize() {
        this.sizeDTO = new SizeDTO();
        return sizeDTO;
    }
}
