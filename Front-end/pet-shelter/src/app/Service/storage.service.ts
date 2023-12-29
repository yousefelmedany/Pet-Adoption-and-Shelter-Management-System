import { Injectable } from '@angular/core';
const USER_KEY = 'auth-user';
const ADOPTER_KEY='auth-adopter';
@Injectable({
  providedIn: 'root'
})
export class StorageService {
  constructor() { }

  clean(): void {
    window.localStorage.clear();
  }
  public saveUser(user: any): void {
    window.localStorage.removeItem(USER_KEY);
    if (user != null) window.localStorage.setItem(USER_KEY, JSON.stringify(user));
  }
  public getUser(): any {
    const user = window.localStorage.getItem(USER_KEY);
    if (user) {
      return JSON.parse(user);
    }
    return null;
  }
  public saveAdopter(adopter: any): void {
    window.localStorage.removeItem(ADOPTER_KEY);
    if (adopter != null) window.localStorage.setItem(ADOPTER_KEY, JSON.stringify(adopter));
  }
  public getAdopter(): any {
    const adopter = window.localStorage.getItem(ADOPTER_KEY);
    if (adopter) {
      return JSON.parse(adopter);
    }
    return null;
  }
  public isLoggedIn(): boolean {
    const user = window.localStorage.getItem(USER_KEY);
    if (user) {
      return true;
    }
    return false;
  }
}
