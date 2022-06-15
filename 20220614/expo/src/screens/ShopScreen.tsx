import { StyleSheet, Text, View } from 'react-native';

const styles = StyleSheet.create({
  container: {
    flex: 1,
    backgroundColor: '#FFF',
    alignItems: 'center',
    justifyContent: 'center',
  },
});

export default function ShopScreen() {
  return (
    <View style={styles.container}>
      <Text>Shop</Text>
    </View>
  );
}
