import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { GetAllCustomerComponent } from './get-all-customer/get-all-customer.component';
const routes: Routes = [
  // other routes...
  { path: 'GetAllCustomer', component: GetAllCustomerComponent }
];
@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
