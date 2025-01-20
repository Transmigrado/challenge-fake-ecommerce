[![codebeat badge](https://codebeat.co/badges/861885d1-541f-4962-9d03-4489d0894b5c)](https://codebeat.co/projects/github-com-transmigrado-challenge-fake-ecommerce-main)

## levantar el proyecto

- abrir proyecto en Android Studio Ladybug 2024.2.2
- sincronizar gradle
- crear un emulador con API 35
- dar play en android studio

## arquitectura UDF

- El **UseCase** maneja la lógica de negocio. En este caso, el **UseCase** puede hacer una llamada a una API para obtener los productos, o realizar alguna otra tarea relacionada con la lógica de negocio.

- Un **componente UI**  necesita realizar una acción, como obtener la lista de productos. Este componente despacha (**dispatch**) una acción a través de Redux.

- La **acción** llega al **Thunk** . El **Thunk** es una función asíncrona que **invoca un UseCase** (por ejemplo, `fetchProducts`), este **Thunk** se encarga de conectar logica de negocio (generalmente llamadas asincronas, llamadas a bases de datos locales, etc) con el estado de Redux.

- El **Reducer** recibe la acción y actualiza el estado en la **Store** con los nuevos datos, como los productos obtenidos.

- Finalmente, como la **Store** ha cambiado, los **componentes UI** que están suscritos a ese estado (por ejemplo, la lista de productos) se **actualizan automáticamente**, todos los componentes que se conectar a la store, deben ser container de otro componente de UI, se explica mas abajo.


## patron container
Se usa el patron container para los componentes que conectan con la store, todos los componentes no deben tener logica de negocio, excepto los container, los container contienen un componente que recibe todo lo necesario para presentar la informacion en pantalla y el container se encarga de extraer los datos, esta separación nos permite aislar logica, minimizando el condigo en los componentes y tambien nos ayuda a poder previsualizar componentes que despliegan informacion simplemente inyectandole un mock  sin tener la necesidad de crear un store.

Este es un ejemplo de patron container, el se encarga de extraer la lista de producto y el estatus de carga, ademas de que le indica a nuestra store que debe ir a buscar productos.

```kotlin
  
@Composable  
fun ProductGridContainer(innerPadding: PaddingValues) {  
  
    val context = LocalContext.current  
    val thunk = remember {  
       EntryPointAccessors.fromApplication(context, ProductsThunkEntryPoint::class.java).getProductsThunk()  
    }  
  
  val products by selectState<AppState, List<Product>> { productsState.list }  
  val isLoading by selectState<AppState, Boolean> { productsState.isLoading }  
  
  val dispatch = rememberDispatcher()  
  
  LaunchedEffect(Unit) {  
	  dispatch(thunk.getProducts())  
  }  
  
  Box(  
        modifier = Modifier.padding(innerPadding)  
    ){  
  ProductGrid(products, isLoading)  
   }  
  
}
```

el componente **ProductGrid**, simplemente despliega la lista de producto y si el estatus de carga esta en true, desplegamos un skeleton view.

## screens
los screens son componentes @Composable, pero llevan este nombre, porque representan las pantallas que navegamos, su tarea es conectar la logica de navegacion, no deben contener logica de negocio ni conectarse de ninguna forma a las store, este es un ejemplo de screen:

```kotlin
@Composable  
fun HomeScreen(navController: NavHostController) {  
    MainDrawer(navController){ innerPadding ->  
	  ProductGridContainer(innerPadding)  
    }  
}
```
aqui indicamos que esta pantalla usa un Drawer y tiene el container de grilla de productos, nada mas. 


