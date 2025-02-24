import { Avatar, Button, Card, CardActions, CardContent, CardHeader, CardMedia, CircularProgress, Link, Typography } from "@mui/material";
import { Product } from "../../app/models/product";

interface Props{
    product : Product;
}
export default function ProductCard({product}: Props){
    const extractImageName = (item: Product): string | null =>{
      if(item && item.pictureUrl){
        const parts = item.pictureUrl.split('/');
        if(parts.length>0){
          return parts[parts.length-1];
        }
      }
      return null;
    }

    const formatPrice = (price: number): string =>{
        return new Intl.NumberFormat('en-In', {
          style:'currency',
          currency: 'INR',
          minimumFractionDigits: 2
        }).format(price);
      }

      
    return(
        <Card> 
            <CardHeader avatar={
                <Avatar sx={{bgcolor: 'secondary.main'}}>
                    {product.name.charAt(0).toUpperCase()}
                </Avatar>
            }
            title={product.name}
            titleTypographyProps={{sx:{fontWeight:'bold', color: 'primary.main' }}}
            />
        <CardMedia
          sx={{ height: 140, backgroundSize:'contain'}}
          image={"/images/products/"+extractImageName(product)}
          title={product.name}
        />
        <CardContent>
          <Typography gutterBottom color='secondary' variant="h5">
            {formatPrice(product.price)}
          </Typography>
          <Typography variant="body2" color="text.secondary">
            {product.productBrand} / {product.productType}
          </Typography>
        </CardContent>
        <CardActions>
        <LoadingButton
          loading={loading}
          onClick={addItem}
          size="small"
          startIcon={loading ? <CircularProgress size={20} color="inherit" /> : null}
        >
          Add to cart
        </LoadingButton> 
          <Button component={Link} to={`/store/${product.id}`} size="small">View</Button>
        </CardActions>
        </Card>
    )
}