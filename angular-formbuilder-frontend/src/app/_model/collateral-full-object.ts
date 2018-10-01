import { CollateralDTO } from './collateral';
import { CollateralDetailDTO } from './collateral-detail';
import { CollateralTransactionDTO } from './collateral-transaction';
import { CollateralTransactionReceiptDTO } from './collateral-transaction-receipt';
import { CollateralTransactionTransitDTO } from './collateral-transaction-transit';
import { CollateralTransactionLoanDTO } from './collateral-transaction-loan';
import { CollateralTransactionReleaseDTO } from './collateral-transaction-release';

export class CollateralFullObject {
    collateralDTO: CollateralDTO;
    collateralDetailDTO: CollateralDetailDTO;
    listCollateralTransactionDTO: CollateralTransactionDTO[];
    listCollateralTransactionReceiptDTO: CollateralTransactionReceiptDTO[];
    listCollateralTransactionTransitDTO: CollateralTransactionTransitDTO[];
    listCollateralTransactionLoanDTO: CollateralTransactionLoanDTO[];
    listCollateralTransactionReleaseDTO: CollateralTransactionReleaseDTO[];
}
