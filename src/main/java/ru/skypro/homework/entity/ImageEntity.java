package ru.skypro.homework.entity;

import lombok.Data;

import javax.persistence.*;

/**
 * Entity class for ad's image
 *
 * @autor Kirill
 */
@Entity
@Data
@Table(name = "image_entity")
public class ImageEntity {
    /**
     * Field id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    /**
     * Field type of media
     */
    private String mediaType;
    /**
     * Field data
     */
    @Lob
    private byte[] data;
    /**
     * Field name of file
     */
    private String fileName;
    /**
     * Field advertisement
     */
    @OneToOne
    @JoinColumn(name = "pk")
    private AdEntity ad;
}
