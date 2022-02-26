package hcl.poc.productService.Service;

import hcl.com.util.NotFoundException;
import hcl.poc.api.Product.ProductDTO;
import hcl.poc.api.User.UserDTO;
import hcl.poc.productService.Model.Product;
import hcl.poc.productService.Repository.ProductRepository;
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

    public void addProduct(ProductDTO productDTO){

        Product product = modelMapper.map(productDTO, Product.class);
        repository.save(product);
    }

    public void modifyProduct(Long id, ProductDTO productDTO){
        Product product = repository.getById(id);

        product.setId(id);
        product.setWeight(productDTO.getWeight());
        product.setName(productDTO.getName());

        repository.save(product);
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
