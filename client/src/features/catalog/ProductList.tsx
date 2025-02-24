import { Grid } from "@mui/material";
import { Product } from "../../app/models/product";
import ProductCard from "./ProductCard";

interface Props{
    products: Product[];
}
export default function ProductLists(products):Props{
    return(
        <Grid container spacing={4}>
            {products.map((product)=>{
                <ProductCard product={product}/>
            })}
        </Grid>
    );
}