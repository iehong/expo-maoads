import { requireNativeViewManager } from "expo-modules-core";
import * as React from "react";

import { ExpoMaoadsViewProps } from "./ExpoMaoadsView.types";

export type Props = ExpoMaoadsViewProps;

const NativeView: React.ComponentType<Props> =
  requireNativeViewManager("ExpoMaoads");

export default function MaoadsView(props: Props) {
  return <NativeView {...props} />;
}
