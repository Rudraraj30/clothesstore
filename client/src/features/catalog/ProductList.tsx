import Grid from "@mui/material/Grid2";
import { Product } from "../../app/models/product";
import ProductCard from "./ProductCard";
interface Props {
  products: Product[];
}
export default function ProductList({ products }: Props) {
  console.log(products)
  return (
    <Grid container spacing={4}>
      {products.map((product) => (
        <Grid key={product.id} size={4}>
          <ProductCard product={product} />
        </Grid>
      ))}
    </Grid>
  );
}
