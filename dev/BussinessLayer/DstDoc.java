package BussinessLayer;
import DataAccessLayer.DstDocDTO;
import DataAccessLayer.ItemToDstDocDTO;

import java.time.LocalDateTime;
import java.util.*;

public class DstDoc {
    private int docNumber;
    private int deliveryNumber;
    private List<Integer> items;
    private Site destination;

    private LocalDateTime estimatedArrivalTime;
    
    private DstDocDTO dstDocDTO;
    public DstDoc(int docNumber, int deliveryNumber, List<Integer> items,Site s,LocalDateTime arrivalTime) throws Exception{
        this.docNumber=docNumber;
        this.deliveryNumber=deliveryNumber;
        this.items=items;
        this.destination = s;
        if (s.isShopKeeperAvailable(arrivalTime)){
            this.estimatedArrivalTime = arrivalTime;
        }
        else { throw new Exception("There is no storekeeper available on destination : " + s.getAddress());}
        List< ItemToDstDocDTO> itemsDTO = new LinkedList<>();
        for (int itemNumber : items)
            itemsDTO.add(new ItemToDstDocDTO(this.docNumber,itemNumber));
        this.dstDocDTO = new DstDocDTO(this.docNumber,this.deliveryNumber,itemsDTO,s.getAddress(),this.estimatedArrivalTime);
        this.dstDocDTO.add();
    }

    public DstDoc(DstDocDTO dstDocDTO,Site site)
    {
        this.docNumber= dstDocDTO.getDocNumber();
        this.deliveryNumber= dstDocDTO.getDeliveryNumber();
        this.destination= site;
        this.estimatedArrivalTime=dstDocDTO.getEstimatedArrivalTime();
        List<Integer> itemsNumbers = new LinkedList<>();
        for (ItemToDstDocDTO itemDTO : dstDocDTO.getItems())
            itemsNumbers.add(itemDTO.getItem());
        this.items= itemsNumbers;
        this.dstDocDTO = dstDocDTO;
    }

    public void removeProducts(List<Integer> deletedProducts)
    {
        for (Integer i : deletedProducts)
            items.remove(i);
        this.dstDocDTO.removeProducts(deletedProducts);
    }

    public int getDocNumber() {
        return docNumber;
    }


    public void setDocNumber(int docNumber) {
        this.docNumber = docNumber;
    }

    public int getDeliveryNumber() {
        return deliveryNumber;
    }

    public void setDeliveryNumber(int deliveryNumber) {
        this.deliveryNumber = deliveryNumber;
    }

    public List<Integer> getItems() {
        return items;
    }

    public void setItems(List<Integer> items) {
        this.items = items;
    }

    public Site getDestination() {
        return destination;
    }

    public void setDestination(Site destination) {
        this.destination = destination;
    }

    public LocalDateTime getEstimatedArrivalTime() {
        return estimatedArrivalTime;
    }

    public void setEstimatedArrivalTime(LocalDateTime estimatedArrivalTime) {
        this.estimatedArrivalTime = estimatedArrivalTime;
    }

    public DstDocDTO getDstDocDTO()
    {
        return this.dstDocDTO;
    }
}