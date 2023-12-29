import { Injectable } from '@angular/core';
import { ActivatedRoute, ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot } from '@angular/router';
import { StorageService } from '../Service/storage.service';


@Injectable({
  providedIn: 'root'
  
})
export class GuardUserService implements CanActivate   {

  constructor(
    private service: StorageService,
    private router: Router,
    private route: ActivatedRoute
  ) {}
  async canActivate(
    route: ActivatedRouteSnapshot,
    state: RouterStateSnapshot
  ): Promise<boolean> {
    try {
      // const observable = this.service.isLoggedIn();
      // const response: any = await observable.toPromise();

      if (this.service.isLoggedIn()== false) {
        this.router.navigate(['']);
        return false;
      } else {
        let user=this.service.getUser();
        if(user.role[0]!='USER'){
          this.router.navigate(['']);
          return false;
        }

        return true;
      }
    } catch (error) {
      alert(error);
      return false;
    }
  }}
