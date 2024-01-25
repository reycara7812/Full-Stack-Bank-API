import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { BrowserModule, provideClientHydration } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HttpClientModule } from '@angular/common/http';
import { GetAllCustomerComponent } from './get-all-customer/get-all-customer.component';
import { GetCustomerByIdComponent } from './get-customer-by-id/get-customer-by-id.component';
@NgModule({
  declarations: [
    AppComponent,
    GetAllCustomerComponent,
    GetCustomerByIdComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule, HttpClientModule,FormsModule
  ],
  providers: [
    provideClientHydration()
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
