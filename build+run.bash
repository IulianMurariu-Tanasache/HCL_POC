./gradlew build

#java -jar microservices/productcompositeservice/build/libs/productCompositeService-v1.0.jar &
java -jar microservices/reviewservice/build/libs/reviewService-v1.0.jar &
java -jar microservices/recommendationservice/build/libs/recommendationService-v1.0.jar &
java -jar microservices/productservice/build/libs/productService-v1.0.jar &
java -jar microservices/userservice/build/libs/userService-v1.0.jar &
