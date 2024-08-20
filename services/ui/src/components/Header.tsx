import * as React from "react";
import {RiShoppingCartFill} from "react-icons/ri";

const Header: React.FC = () => {
    return (
        <div className="flex justify-between items-center mb-6">
            <h1 className="text-3xl font-bold">shopping cart</h1>
            <div className="relative">
                <RiShoppingCartFill className="text-2xl"/>
                <span
                    className="bg-red-500 text-white flex w-4 h-4 justify-center items-center rounded-full absolute -top-1 -right-2 text-xs"
                >
                    0
                </span>
            </div>
        </div>
    );
}

export default Header;