package konex.innovation.medicine_administration.services;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import jakarta.annotation.Resource;
import konex.innovation.medicine_administration.dao.SaleDao;
import konex.innovation.medicine_administration.domain.Sale;

@Service
public class SaleServiceImp implements CrudService<Sale> {

    @Resource
    private SaleDao dao;

    @Override
    public List<Sale> list() {
        ArrayList<Sale> sales = (ArrayList<Sale>) dao.findAll();
        for (Sale sale : sales) {
            // Verifica que los datos traidos no tengan fecha de eliminacion
            if (!sale.getDeletedAt().isEqual(null)) {
                sales.remove(sale);
            }
        }
        return sales;
    }

    @Override
    public Sale findById(Long id) {
        return dao.findById(id).orElse(null);
    }

    @Override
    public Sale save(Sale entity) {
        return dao.save(entity);
    }

    @Override
    public Sale delete(Sale entity) {
        entity.setDeletedAt(LocalDateTime.now());
        return dao.save(entity);
    }

}