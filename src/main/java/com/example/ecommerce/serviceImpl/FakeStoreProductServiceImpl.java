package com.example.ecommerce.serviceImpl;

import com.example.ecommerce.dto.GetSingleProductDTO;
import com.example.ecommerce.dto.Response;
import com.example.ecommerce.exceptionHandler.CustomExceptionService;
import com.example.ecommerce.exceptionHandler.ExceptionLogger;
import com.example.ecommerce.helper.RestTempleteConfiguration;
import com.example.ecommerce.models.CommonConstants;
import com.example.ecommerce.models.Product;
import com.example.ecommerce.service.ProductService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.annotation.Primary;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
@Primary
public class FakeStoreProductServiceImpl implements ProductService {

    private static final Logger LOG = LogManager.getFormatterLogger();


    private final RestTempleteConfiguration restTempleteConfiguration;
    private RestTemplate restTemplate;
    private ObjectMapper objectMapper;


    public FakeStoreProductServiceImpl(RestTempleteConfiguration restTempleteConfiguration, RestTemplate restTemplate, ObjectMapper objectMapper) {
        this.restTempleteConfiguration = restTempleteConfiguration;
        this.restTemplate = restTemplate;
        this.objectMapper = objectMapper;
    }

    @Override
    public Product getProductById(long id, String logID) throws CustomExceptionService {
        GetSingleProductDTO getSingleProductDTO = null;
        Response response = new Response();
        try {
            getSingleProductDTO = restTemplate.getForObject(CommonConstants.FAKE_STORE_URL + "/" + id, GetSingleProductDTO.class);

        } catch (Exception e) {
            LOG.error(logID + " Error occur while getting product by ID ");
            ExceptionLogger.printException(e, logID);
            throw new CustomExceptionService(" custom exception ");
        }
        return convertToProduct(getSingleProductDTO, logID);
    }

    @Override
    public List<Product> getAllProducts(String logID) {
        GetSingleProductDTO[] forObject = null;
        try {
            forObject = restTemplate.getForObject(CommonConstants.FAKE_STORE_URL, GetSingleProductDTO[].class);
        } catch (Exception e) {
            LOG.error(logID + " Error occur while getting all product. ");
            ExceptionLogger.printException(e, logID);
        }
        return convertToProductList(forObject);
    }

    @Override
    public Product createProduct(Product product, String logID) {
        GetSingleProductDTO getSingleProductDTO = null;
        try {
            String stringJosn = convertProductToJson(product);
            getSingleProductDTO = restTemplate.postForObject("https://fakestoreapi.com/products", product, GetSingleProductDTO.class);

        } catch (Exception e) {
            LOG.error(logID + " Error occur while creating product ");
            ExceptionLogger.printException(e, logID);
        }
        return convertToProduct(getSingleProductDTO, logID);
    }

    @Override
    public Product deleteProductById(long id, String logID) {
        GetSingleProductDTO getSingleProductDTO = null;
        try {
            ResponseEntity<GetSingleProductDTO> exchange = restTemplate.exchange(CommonConstants.FAKE_STORE_URL + "/" + 2, HttpMethod.DELETE, null, GetSingleProductDTO.class);
            getSingleProductDTO = exchange.getBody();
        } catch (Exception e) {
            LOG.error(logID + " Error occur while deleting product by ID ");
            ExceptionLogger.printException(e, logID);
        }
        return convertToProduct(getSingleProductDTO, logID);
    }

    @Override
    public Product updateProduct(Product product, String logID) {
        GetSingleProductDTO getSingleProductDTOResponseApi = null;
        try {
//            GetSingleProductDTO getSingleProductDTO = convertToDTO(product);
//
//            getSingleProductDTOResponseApi =restTempleteConfiguration.getRestTemplete().postForObject(CommonConstants.FAKE_STORE_URL + "/" + product.getId(), getSingleProductDTO, GetSingleProductDTO.class);

        } catch (Exception e) {
            LOG.error(logID + " Error occur while updating product ");
            ExceptionLogger.printException(e, logID);
        }


        return convertToProduct(getSingleProductDTOResponseApi, logID);
    }


    public Product convertToProduct(GetSingleProductDTO dto, String logID) {
        Product product = new Product();
        try {
            if (!ObjectUtils.isEmpty(dto)) {
                // Manually map fields from FakeStoreProductDetailsDTO to Product
                product.setId(dto.getId());  // Assuming int -> long conversion is safe here. If not, convert explicitly.
                product.setTitle(dto.getTitle());  // Assuming title exists in both DTO and Product
                product.setPrice(dto.getPrice());  // Assuming price exists in both DTO and Product
                product.setDescription(dto.getDescription());  // Ensure description is present in both
//                product.setCategory(dto.getCategory());  // Category field mapping
                product.setImage(dto.getImage());  // Image field mapping
            }
        } catch (Exception e) {
            ExceptionLogger.printException(e, logID);
            LOG.error(logID + " Error occur while converting DTO to Product object ");
        }
        return product;
    }

    public List<Product> convertToProductList(GetSingleProductDTO[] dtoList) {
        List<Product> productList = new ArrayList<Product>();
        for (GetSingleProductDTO dto : dtoList) {
            Product product = new Product();
            // Manually map fields from FakeStoreProductDetailsDTO to Product
            product.setId(dto.getId());  // Assuming int -> long conversion is safe here. If not, convert explicitly.
            product.setTitle(dto.getTitle());  // Assuming title exists in both DTO and Product
            product.setPrice(dto.getPrice());  // Assuming price exists in both DTO and Product
            product.setDescription(dto.getDescription());  // Ensure description is present in both
//            product.setCategory(dto.getCategory());  // Category field mapping
            product.setImage(dto.getImage());  // Image field mapping
            productList.add(product);
        }
        return productList;
    }

    public String convertProductToJson(Product productDTO) throws JsonProcessingException {
        return objectMapper.writeValueAsString(productDTO);
    }


    public GetSingleProductDTO convertToDTO(Product product) {
        GetSingleProductDTO dto = new GetSingleProductDTO();

        // Mapping fields from Product entity to DTO
        dto.setId((int) product.getId());  // Assuming Product's id is long, and DTO expects int
        dto.setTitle(product.getTitle());
        dto.setPrice(product.getPrice());
        dto.setDescription(product.getDescription());
//        dto.setCategory(product.getCategory());
        dto.setImage(product.getImage());

        return dto;
    }

//    public void setBaseResponse(BaseResponse<Product> baseResponse, Response response,  List<T> data){
//       response.setMessage("");
//
//        baseResponse.setResponse(re);
//    }

}

