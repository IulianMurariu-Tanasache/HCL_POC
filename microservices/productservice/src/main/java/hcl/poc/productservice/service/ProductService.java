package hcl.poc.productservice.service;

import hcl.com.util.NotFoundException;
import hcl.poc.api.product.ProductDTO;
import hcl.poc.productservice.model.Product;
import hcl.poc.productservice.repository.ProductRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository repository;

    @Autowired
    private ModelMapper modelMapper;

    public ProductDTO addProduct(ProductDTO productDTO){

        Product product = modelMapper.map(productDTO, Product.class);
        repository.save(product);

        return modelMapper.map(product, ProductDTO.class);
    }

    public ProductDTO modifyProduct(Long id, ProductDTO productDTO){
        Product product = repository.getById(id);

        product.setId(id);
        product.setWeight(productDTO.getWeight());
        product.setName(productDTO.getName());

        repository.save(product);

        return modelMapper.map(product,ProductDTO.class);
    }

    public void deleteProduct(Long id){
        repository.deleteById(id);
    }

    public List<ProductDTO> getAllProducts(){

        List<Product> listEntity = repository.findAll();
        List<ProductDTO> listDTO = new ArrayList<>();

        for(Product product : listEntity){
            listDTO.add(modelMapper.map(product, ProductDTO.class));
        }

        return listDTO;
    }

    public ProductDTO getOneProduct(Long id){
        return modelMapper.map(repository.findById(id).orElseThrow(NotFoundException::new), ProductDTO.class);

    }
}
