import { Subject } from 'rxjs/Subject';
import { Injectable } from '@angular/core';
import 'rxjs/add/operator/toPromise';
import { KeyValueModel } from '../_model/key-value';
import { ErrorObservable } from 'rxjs/observable/ErrorObservable';
import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';
import { SelectedKeyValue } from '../_model/selected-key-value';
import { environment } from '../../environments/environment';
import { BehaviorSubject } from '../../../node_modules/rxjs';
// import { catchError } from 'rxjs/operators';
@Injectable()
export class LookUpService {
    private baseUrl = environment.apiUrl;  // URL to web api

    selectedKeyValues: SelectedKeyValue[] = [];

    public selectedKeyValueSubject: Subject<SelectedKeyValue[]> = new BehaviorSubject<SelectedKeyValue[]>([]);;

    constructor(private http: HttpClient) {
        this.selectedKeyValueSubject = new Subject<SelectedKeyValue[]>();
    }

    addSelectedKeyValue(selected: SelectedKeyValue) {
        for (var i = 0; i < this.selectedKeyValues.length; i++) {
            if (this.selectedKeyValues[i].lkpValue == selected.lkpValue) {
                this.selectedKeyValues[i] = selected;
                this.selectedKeyValueSubject.next(this.selectedKeyValues);
                return;
            }
        }
        this.selectedKeyValues.push(selected);
        this.selectedKeyValueSubject.next(this.selectedKeyValues);
    }

    getLookUp(idlkp: string): Observable<KeyValueModel[]> {
        return this.http.get<KeyValueModel[]>(this.baseUrl + '/param/' + idlkp);

        //.pipe(catchError(this.handleError));
    }

    getLookUpByParentId(idlkp: string, parentId: string): Observable<KeyValueModel[]> {
        return this.http.get<KeyValueModel[]>(this.baseUrl + '/param/' + idlkp + '/' + parentId);
    }

    getParentValue(lkpValue: string): string {
        let result: string = null;
        for (var i = 0; i < this.selectedKeyValues.length; i++) {
            if (this.selectedKeyValues[i].lkpValue == lkpValue) {
                return this.selectedKeyValues[i].selected;
            }
        }
        return result;
    }

    getSelectedKeyValues(): SelectedKeyValue[] {
        return this.selectedKeyValues;
    }


    isAlreadyExist(selected: SelectedKeyValue): boolean {
        for (var i = 0; i < this.selectedKeyValues.length; i++) {
            if (this.selectedKeyValues[i].lkpValue == selected.lkpValue) {
                return true;
            }
        }
        return false;
    }

    private handleError(error: HttpErrorResponse) {
        if (error.error instanceof ErrorEvent) {
            // A client-side or network error occurred. Handle it accordingly.
            console.error('An error occurred:', error.error.message);
        } else {
            // The backend returned an unsuccessful response code.
            // The response body may contain clues as to what went wrong,
            console.error(
                `Backend returned code ${error.status}, ` +
                `body was: ${error.error}`);
        }
        // return an ErrorObservable with a user-facing error message
        return new ErrorObservable(
            'Something bad happened; please try again later.');
    }

}
