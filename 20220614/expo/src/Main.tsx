import { NavigationContainer } from '@react-navigation/native';
import { createBottomTabNavigator } from '@react-navigation/bottom-tabs';

import Ionicons from 'react-native-vector-icons/Ionicons';

import HomeScreen from './screens/HomeScreen';
import ShopScreen from './screens/ShopScreen';
import ProfileScreen from './screens/ProfileScreen';
import SpacesScreen from './screens/SocialScreen';
import WalletScreen from './screens/WalletScreen';

const Tab = createBottomTabNavigator();

export default function Main() {
  return (
    <NavigationContainer>
      <Tab.Navigator
        screenOptions={({ route }) => ({
          // eslint-disable-next-line react/no-unstable-nested-components
          tabBarIcon: ({ focused, color, size }) => {
            const iconName = {
              Home: `home${focused ? '' : '-outline'}`,
              Shop: `pricetag${focused ? '' : '-outline'}`,
              Profile: `person${focused ? '' : '-outline'}`,
              Social: `heart${focused ? '' : '-outline'}`,
              Wallet: `wallet${focused ? '' : '-outline'}`,
            }[route.name];
            return <Ionicons name={iconName!} size={size} color={color} />;
          },
          tabBarActiveTintColor: 'green',
          tabBarInactiveTintColor: 'gray',
        })}
      >
        <Tab.Screen name="Home" component={HomeScreen} />
        <Tab.Screen name="Shop" component={ShopScreen} />
        <Tab.Screen name="Profile" component={ProfileScreen} />
        <Tab.Screen name="Social" component={SpacesScreen} />
        <Tab.Screen name="Wallet" component={WalletScreen} />
      </Tab.Navigator>
    </NavigationContainer>
  );
}
