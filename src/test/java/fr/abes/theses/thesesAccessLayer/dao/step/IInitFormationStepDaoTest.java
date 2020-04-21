package fr.abes.theses.thesesAccessLayer.dao.step;

import fr.abes.theses.thesesAccessLayer.ThesesAccessLayerApplication;
import fr.abes.theses.thesesAccessLayer.dao.star.IInitFormationStarDao;
import fr.abes.theses.thesesAccessLayer.model.entities.star.InitFormationStar;
import fr.abes.theses.thesesAccessLayer.model.entities.step.InitFormationStep;
import fr.abes.theses.thesesAccessLayer.model.types.HibernateXMLType;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.File;
import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = ThesesAccessLayerApplication.class)
@EnableTransactionManagement
public class IInitFormationStepDaoTest {
    private InitFormationStep initFormation;

    @Autowired
    private IInitFormationStepDao initFormationDao;

    @BeforeEach
    public void init() throws ParserConfigurationException, SAXException, IOException {
        initFormation = getInitFormation();
    }

    @AfterEach
    public void end() {
        initFormationDao.delete(initFormation);
    }

    @Test
    public void testFindById() throws TransformerException {
        InitFormationStep initFormationIn = initFormationDao.save(initFormation);
        InitFormationStep initFormationOut = initFormationDao.findById(initFormationIn.getId()).get();
        assertThat(initFormationOut.getCodeEtab()).isEqualTo(initFormationIn.getCodeEtab());
        assertThat(HibernateXMLType.domToString(initFormationOut.getDoc())).isEqualTo(HibernateXMLType.domToString(initFormationIn.getDoc()));
    }

    @Test
    public void testDeleteById() {
        InitFormationStep initFormationStep = initFormationDao.save(initFormation);
        initFormationDao.deleteById(initFormationStep.getId());
        assertThat(initFormationDao.findById(initFormationStep.getId())).isEmpty();
    }


    private InitFormationStep getInitFormation() throws IOException, SAXException, ParserConfigurationException {
        String filePath = getClass().getClassLoader().getResource("initFormation.xml").getPath();
        File fXmlFile = new File(filePath);
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        Document doc = dBuilder.parse(fXmlFile);
        InitFormationStep initFormation = new InitFormationStep();
        initFormation.setIdDoc(999999);
        initFormation.setCodeEtab("TEST");
        initFormation.setDoc(doc);
        return initFormation;
    }
}
