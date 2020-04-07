package fr.abes.theses.thesesAccessLayer.model.entities.portail;

import fr.abes.theses.thesesAccessLayer.model.entities.GenericEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;


@Table(name = "METADATA_TEF")
@NoArgsConstructor
@Getter @Setter
public class MetadataTef implements Serializable, GenericEntity<Integer> {
    @Id
    @Column(name = "OAIRECORD_ID")
    private Integer oaiRecordId;
    @Lob
    @Column(name = "METADATA")
    private String metadata;

    public MetadataTef(Integer oaiRecordId, String metadata) {
        this.oaiRecordId = oaiRecordId;
        this.metadata = metadata;
    }

    @Override
    public Integer getId() {
        return this.oaiRecordId;
    }
}