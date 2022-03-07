import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { RouterModule } from '@angular/router';
import { HttpClientModule } from '@angular/common/http'

import { AppComponent } from './app.component';
import { FoodsearchComponent } from './foodsearch/foodsearch.component';
import { MapComponent } from './map/map.component';
import { foodService } from './food.service';
import { FoodDetailComponent } from './food-detail/food-detail.component';
import { FormComponent } from './form/form.component';
import { LoginComponent } from './login/login.component';
import { RegistrationComponent } from './registration/registration.component';
import { FavouritelistComponent } from './favouritelist/favouritelist.component';

const appRoute = [
  {path:'',component:LoginComponent},
  {path:'registration',component:RegistrationComponent},
  {path:'favourite/:username',component:FavouritelistComponent},
  {path:'food/:username/:uuid',component:FoodDetailComponent},
  {path:'search/:username',component:FoodsearchComponent},
  {path:'map/:username',component:MapComponent},
  {path:'form',component:FormComponent} ,
  {path:'**',redirectTo:'/',pathMatch:'full'}
]

@NgModule({
  declarations: [
    AppComponent,
    MapComponent,
    FoodsearchComponent,
    FoodDetailComponent,
    FormComponent,
    LoginComponent,
    RegistrationComponent,
    FavouritelistComponent,
  ],
  imports: [
    BrowserModule,
    FormsModule,
    ReactiveFormsModule,
    RouterModule.forRoot(appRoute, {useHash:true}),
    HttpClientModule
  ],
  providers: [foodService],
  bootstrap: [AppComponent]
})
export class AppModule { }
