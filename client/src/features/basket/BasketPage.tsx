import { Remove, Add } from "@mui/icons-material";
import {
  IconButton,
  Paper,
  Table,
  TableBody,
  TableCell,
  TableContainer,
  TableHead,
  TableRow,
  Typography,
} from "@mui/material";
import { BasketItem } from "../../app/models/basket";
import { useAppDispatch, useAppSelector } from "../../app/store/ConfigureStore";
import agent from "../../app/api/agent";
import { Product } from "../../app/models/product";
import DeleteIcon from '@mui/icons-material/Delete';

function BasketPage() {
  const {basket}= useAppSelector((state) => state.basket);
  const dispatch = useAppDispatch();
  const { BasketStore: BasketActions } = agent;

  const removeItem = (productId: number) => {
    BasketActions.removeItem(productId, dispatch);
  };

  const decrementItem = (productId: number, quantity: number=1) => {
    BasketActions.decrementItemQuantity(productId, quantity, dispatch);
  };

  const incrementItem = (productId: number, quantity: number = 1) => {
    BasketActions.incrementItemQuantity(productId, quantity, dispatch);
  };

  const extractImageName = (item: Product): string | null => {
    if (item && item.pictureUrl) {
      const parts = item.pictureUrl.split("/");
      if (parts.length > 0) {
        return parts[parts.length - 1];
      }
    }
    return null;
  };

  // Function to format the price with USD currency symbol
  const formatPrice = (price: number): string => {
    return new Intl.NumberFormat("en-IN", {
      style: "currency",
      currency: "USD",
      minimumFractionDigits: 2,
    }).format(price);
  };

  if (!basket || basket.items.length == 0) {
    return (
      <Typography variant="h3">Your Basket Is empty, Add few Items</Typography>
    );
  }
  return (
    <TableContainer component={Paper}>
      <Table>
        <TableHead>
          <TableRow>
            <TableCell>Product Image</TableCell>
            <TableCell>Product</TableCell>
            <TableCell>Price</TableCell>
            <TableCell>Quantity</TableCell>
            <TableCell>Subtotal</TableCell>
            <TableCell>Remove</TableCell>
          </TableRow>
        </TableHead>
        <TableBody>
          {basket.items.map((item: BasketItem) => (
            <TableRow key={item.id}>
              <TableCell>
                {item.pictureUrl && (
                  <img
                    src={"/images/products/" + extractImageName(item)}
                    alt="Product"
                    width="50"
                    height="50"
                  />
                )}
              </TableCell>
              <TableCell>{item.name}</TableCell>
              <TableCell>{formatPrice(item.price)}</TableCell>
              <TableCell>
                <IconButton
                  color="error"
                  onClick={() => decrementItem(item.id)}
                >
                  <Remove />
                </IconButton>
                {item.quantity}
                <IconButton
                  color="error"
                  onClick={() => incrementItem(item.id)}
                >
                  <Add />
                </IconButton>
              </TableCell>
              <TableCell>{formatPrice(item.price * item.quantity)}</TableCell>
              <TableCell>
                <IconButton
                  onClick={() => removeItem(item.id)}
                  aria-label="delete"
                >
                  <DeleteIcon />
                </IconButton>
              </TableCell>
            </TableRow>
          ))}
        </TableBody>
      </Table>
    </TableContainer>
  );
}

export default BasketPage;
