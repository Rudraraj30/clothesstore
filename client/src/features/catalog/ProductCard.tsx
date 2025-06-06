import {
  Avatar,
  Button,
  Card,
  CardActions,
  CardContent,
  CardHeader,
  CardMedia,
  CircularProgress,
  Typography,
} from "@mui/material";
import { Product } from "../../app/models/product";
import { Link } from "react-router-dom";
import { useAppDispatch } from "../../app/store/ConfigureStore";
import agent from "../../app/api/agent";
import { setBasket } from "../basket/basketSlice";
import { useState } from "react";
import { LoadingButton } from "@mui/lab";

interface Props {
  product: Product;
}

function ProductCard({ product }: Props) {
  const extractImageName = (item: Product): string | null => {
    if (item && item.pictureUrl) {
      const parts = item.pictureUrl.split("/");
      if (parts.length > 0) {
        return parts[parts.length - 1];
      }
    }
    return null;
  };
  function formatPrice(price: number): string {
    return new Intl.NumberFormat("en-In", {
      style: "currency",
      currency: "USD",
      minimumFractionDigits: 2,
    }).format(price);
  }
  const [loading, setLoading] = useState(false);
  const dispatch = useAppDispatch();
  function addItem() {
    setLoading(true);
    
    agent.BasketStore.addItem(product, dispatch)
      .then((response) => {
        dispatch(setBasket(response.basket));
      })
      .catch((error) => console.error(error))
      .finally(() => setLoading(false));
  }
  return (
    <Card>
      <CardHeader
        avatar={
          <Avatar sx={{ bgcolor: "secondary.main" }}>
            {product.name.charAt(0).toUpperCase()}
          </Avatar>
        }
        title={product.name}
        titleTypographyProps={{
          sx: { fontWeight: "bold", color: "primary.main" },
        }}
      />
      <CardMedia
        sx={{ height: 140, backgroundSize: "contain" }}
        image={"/images/products/" + extractImageName(product)}
        title={product.name}
      />
      <CardContent>
        <Typography gutterBottom color="secondary" variant="h5">
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
          startIcon={
            loading ? <CircularProgress size={20} color="inherit" /> : null
          }
        >
          Add to cart
        </LoadingButton>
        <Button size="small" component={Link} to={`/store/${product.id}`}>
          View
        </Button>
      </CardActions>
    </Card>
  );
}

export default ProductCard;
