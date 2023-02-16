import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class ProductLists extends Frame implements ActionListener {
    
    private Label productLabel;
    private TextField productTextField;
    private Label quantityLabel;
    private TextField quantityTextField;
    private Button addButton;
    private java.awt.List productList;
    private java.awt.List uniqueProductList;
    private HashMap<String, Integer> products;

    public ProductLists() {
        setLayout(new FlowLayout());
        productLabel = new Label("Product:");
        add(productLabel);
        productTextField = new TextField(10);
        add(productTextField);
        quantityLabel = new Label("Quantity:");
        add(quantityLabel);
        quantityTextField = new TextField(10);
        add(quantityTextField);
        addButton = new Button("Add");
        addButton.addActionListener(this);
        add(addButton);
        productList = new java.awt.List(10);
        add(productList);
        uniqueProductList = new java.awt.List(10);
        add(uniqueProductList);
        products = new HashMap<String, Integer>();
        setTitle("Product List");
        setSize(400, 300);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == addButton) {
            String product = productTextField.getText();
            int quantity = Integer.parseInt(quantityTextField.getText());
            productList.add(product + " (" + quantity + ")");
            if (products.containsKey(product)) {
                int oldQuantity = products.get(product);
                products.put(product, oldQuantity + quantity);
            } else {
                products.put(product, quantity);
            }
            uniqueProductList.removeAll();
            Set<Map.Entry<String, Integer>> entrySet = products.entrySet();
            for (Map.Entry<String, Integer> entry : entrySet) {
                uniqueProductList.add(entry.getKey() + " (" + entry.getValue() + ")");
            }
        }
    }

    public static void main(String[] args) {
        new ProductLists();
    }
}
