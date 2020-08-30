package fit5042.tutex.repository;

import fit5042.tutex.repository.entities.ContactPerson;
import fit5042.tutex.repository.entities.Property;
import java.util.List;
import java.util.Set;
//import java.util.function.Predicate;
import javax.persistence.criteria.Predicate;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.print.attribute.standard.PDLOverrideSupported;

import com.sun.xml.rpc.processor.modeler.j2ee.xml.packageMappingType;

/**
 *
 * @author Eddie Leung
 */
@Stateless
public class JPAPropertyRepositoryImpl implements PropertyRepository {

    //insert code (annotation) here to use container managed entity manager to complete these methods  
    @PersistenceContext (unitName = "W4ExeSolution-ejbPU")
	private EntityManager entityManager;

    @Override
    public void addProperty(Property property) throws Exception {
        List<Property> properties = entityManager.createNamedQuery(Property.GET_ALL_QUERY_NAME).getResultList();
        property.setPropertyId(properties.get(0).getPropertyId() + 1);
        entityManager.persist(property);
    }

    @Override
    public Property searchPropertyById(int id) throws Exception {
        Property property = entityManager.find(Property.class, id);
        property.getTags();
        return property;
    }

    @Override
    public List<Property> getAllProperties() throws Exception {
        return entityManager.createNamedQuery(Property.GET_ALL_QUERY_NAME).getResultList();
    }

    @Override
    public Set<Property> searchPropertyByContactPerson(ContactPerson contactPerson) throws Exception {
        contactPerson = entityManager.find(ContactPerson.class, contactPerson.getConactPersonId());
        contactPerson.getProperties().size();
        entityManager.refresh(contactPerson);

        return contactPerson.getProperties();
    }

    @Override
    public List<ContactPerson> getAllContactPeople() throws Exception {
        return entityManager.createNamedQuery(ContactPerson.GET_ALL_QUERY_NAME).getResultList();
    }

    @Override
    public void removeProperty(int propertyId) throws Exception {
        //complete this method
    	Property property = entityManager.find(Property.class, propertyId);
    	
    	if (property != null) {
    		entityManager.remove(property);
    	}
    }

    @Override
    public void editProperty(Property property) throws Exception {
        try {
            entityManager.merge(property);
        } catch (Exception ex) {

        }
    }

    @Override
    public List<Property> searchPropertyByBudget(double budget) throws Exception {
        // interface working as a factory for query
    	CriteriaBuilder builder = entityManager.getCriteriaBuilder();
    	// criteria query
    	CriteriaQuery criteriaQuery = builder.createQuery(Property.class);
    	//query root, specify the domain objects which will be fetched
    	Root<Property> p = criteriaQuery.from(Property.class);
    	criteriaQuery.select(p);
    	// filtering data
    	Predicate predicate = builder.lessThanOrEqualTo(p.get("price").as(Double.class), budget);
        // store the criteria in query
    	criteriaQuery.where(predicate);
    	// create a typed query(type-safe query)
        TypedQuery<Property> typedQuery = entityManager.createQuery(criteriaQuery);
        
    	return typedQuery.getResultList();
    }
}
