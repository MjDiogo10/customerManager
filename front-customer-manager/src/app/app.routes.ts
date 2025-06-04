import { Routes } from '@angular/router';
import {ListCustomerComponent} from './customer/pages/list-customer/list-customer.component';
import {CreateCustomerComponent} from './customer/pages/create-customer/create-customer.component';
import {UpdateCustomerComponent} from './customer/pages/update-customer/update-customer.component';

export const routes: Routes = [
  {
    path: '',
    redirectTo: '/customers',
    pathMatch: 'full'
  },
  {
    path: 'customers',
    component: ListCustomerComponent
  },
  {
    path: 'customers/create',
    component: CreateCustomerComponent
  },
  {
    path: 'customers/update/:id',
    component: UpdateCustomerComponent
  }
];
