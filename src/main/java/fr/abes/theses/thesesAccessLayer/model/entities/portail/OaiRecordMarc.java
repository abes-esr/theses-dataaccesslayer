package fr.abes.theses.thesesAccessLayer.model.entities.portail;

import fr.abes.theses.thesesAccessLayer.model.entities.GenericEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "OAIRECORD_MARC")
@NoArgsConstructor
@Getter @Setter
public class OaiRecordMarc implements Serializable, GenericEntity<Integer> {
    @Id
    @Column(name = "OAIRECORD_ID")
    private Integer oaiRecordId;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "DATEINSERTION")
    private Calendar dateInsertion;

    @Column(name = "OAIIDENTIFIER")
    private String oaiIdentifier;

    @OneToMany(mappedBy = "oaiRecordId")
    private Set<RecSetMarc> recSetMarcs;

    @OneToMany(mappedBy = "oaiRecordId")
    private Set<MetadataMarc> metadataMarcs;

    public OaiRecordMarc(Integer oaiRecordId, Calendar dateInsertion, String oaiIdentifier) {
        this.oaiRecordId = oaiRecordId;
        this.dateInsertion = dateInsertion;
        this.oaiIdentifier = oaiIdentifier;
    }

    @Override
    public Integer getId() {
        return this.oaiRecordId;
    }
}
