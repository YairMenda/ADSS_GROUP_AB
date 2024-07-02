package DataAccessLayer;

import java.time.LocalDateTime;
import java.util.List;

public class DstDocDTO {
    private int docNumber;
    private int deliveryNumber;
    private List<ItemToDstDocDTO> items;
    private String destination;
    private LocalDateTime estimatedArrivalTime;
    private DstDocController controller;


    public DstDocDTO(int docNumber, int deliveryNumber, List<ItemToDstDocDTO> items, String destination, LocalDateTime estimatedArrivalTime) {
        this.docNumber = docNumber;
        this.deliveryNumber = deliveryNumber;
        this.items = items;
        this.destination = destination;
        this.estimatedArrivalTime = estimatedArrivalTime;
        this.controller = new DstDocController();
    }

    public int getDocNumber() {
        return docNumber;
    }

    public int getDeliveryNumber() {
        return deliveryNumber;
    }

    public List<ItemToDstDocDTO> getItems() {
        return items;
    }

    public String getDestination() {
        return destination;
    }

    public LocalDateTime getEstimatedArrivalTime() {
        return estimatedArrivalTime;
    }


    public boolean add() {
        for (ItemToDstDocDTO itemDTO : this.getItems())
            itemDTO.add();
        return this.controller.add(this);
    }

    public boolean remove() {
        return this.controller.remove(this);
    }

    public boolean removeProducts(List<Integer> itemsToDelete) {
        for (ItemToDstDocDTO itemDTO : this.getItems())
            if (itemsToDelete.contains(itemDTO.getItem())) {
                itemDTO.remove();
                this.items.remove(itemDTO);
            }
        return true;
    }
}
