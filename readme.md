# Deep clone

Just for fun :)

## Libs

### org.apache.commons 

Example clone: `SerializationUtils.clone(obj)`

### com.google.gson 

Example clone: `new Gson().fromJson(gson.toJson(obj), Object.class)`

cons:  
* problems with java.util.Date, must create adapter, see: `GsonJsonCloneTest.java`

### com.fasterxml.jackson

Example clone: `new ObjectMapper().readValue(objectMapper.writeValueAsString(obj), Object.class)`

cons:  
* need to add the default constructor
* checked exceptions